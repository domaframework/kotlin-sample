import org.jetbrains.kotlin.gradle.dsl.JvmTarget

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.h2database:h2:1.4.200")
    }
}

plugins {
    id("application")
    id("com.diffplug.spotless") version "7.0.2"
    id("org.domaframework.doma.codegen") version "3.0.0"
    id("org.domaframework.doma.compile") version "3.0.1"
    kotlin("jvm") version "2.1.20"
    kotlin("kapt") version "2.1.20"
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}
application {
    mainClass.set("sample.AppKt")
}

dependencies {
    val domaVersion: String by project
    kapt("org.seasar.doma:doma-processor:$domaVersion")
    implementation("org.seasar.doma:doma-kotlin:$domaVersion")
    implementation("org.seasar.doma:doma-slf4j:$domaVersion")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.18")
    runtimeOnly("com.h2database:h2:1.4.200")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.4")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

spotless {
    kotlin {
        ktlint("1.1.1")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

val h2Url = "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1"
val h2User = "sa"
val h2Password = ""

domaCodeGen {
    register("dev") {
        url.set(h2Url)
        user.set(h2User)
        password.set(h2Password)
        languageType.set(org.seasar.doma.gradle.codegen.desc.LanguageType.KOTLIN)
        entity {
            packageName.set("sample.entity")
            val mappingFile = layout.projectDirectory.file("$projectDir/codegen-name-mapping.properties")
            entityPropertyClassNamesFile.set(mappingFile)
        }
    }
}

tasks {
    val jvmTarget = JvmTarget.JVM_17

    compileKotlin {
        compilerOptions.jvmTarget = jvmTarget
    }

    compileTestKotlin {
        compilerOptions.jvmTarget = jvmTarget
    }

    test {
        maxHeapSize = "1g"
        useJUnitPlatform()
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    val createDb by registering {
        doLast {
            val ds = org.seasar.doma.gradle.codegen.jdbc.SimpleDataSource()
            ds.driver = org.h2.Driver()
            ds.url = h2Url
            ds.user = h2User
            ds.password = h2Password
            ds.connection.use { con ->
                con.createStatement().use { stmt ->
                    stmt.execute("RUNSCRIPT FROM '${project.projectDir}/src/main/resources/META-INF/sample/dao/ScriptDao/create.script'")
                }
            }
        }
    }

    named("domaCodeGenDevDbMeta") {
        dependsOn(createDb)
    }

    build {
        dependsOn(spotlessApply)
    }
}
