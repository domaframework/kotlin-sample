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
    id("com.diffplug.spotless") version "5.5.0"
    id("org.seasar.doma.codegen") version "1.2.1"
    id("org.seasar.doma.compile") version "1.1.0"
    kotlin("jvm") version "1.4.0"
    kotlin("kapt") version "1.4.0"
}

application {
    mainClassName = "sample.AppKt"
}

dependencies {
    kapt("org.seasar.doma:doma-processor:2.41.0")
    implementation("org.seasar.doma:doma-core:2.41.0")
    runtimeOnly("com.h2database:h2:1.4.200")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

spotless {
    kotlin {
        ktlint("0.38.1")
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
            entityPropertyClassNamesFile.fileValue(file("$projectDir/codegen-name-mapping.properties"))
        }
    }
}

tasks {
    val jvmTarget = "11"

    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }

    test {
        maxHeapSize = "1g"
        useJUnitPlatform()
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
