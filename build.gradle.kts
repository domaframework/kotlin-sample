import org.jetbrains.kotlin.gradle.dsl.JvmTarget

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.jdbc.h2)
    }
}

plugins {
    id("application")
    alias(libs.plugins.spotless)
    alias(libs.plugins.doma.codegen)
    alias(libs.plugins.doma.compile)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
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
    kapt(libs.doma.processor)
    implementation(libs.doma.kotlin)
    implementation(libs.doma.slf4j)
    runtimeOnly(libs.logback.classic)
    runtimeOnly(libs.jdbc.h2)
    // Use JUnit BOM for version management
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
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
