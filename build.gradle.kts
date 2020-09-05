plugins {
    id("application")
    id("org.seasar.doma.compile") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
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

ktlint {
    version.set("0.38.1")
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
}
