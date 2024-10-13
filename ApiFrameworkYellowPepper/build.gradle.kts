plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.gatling.gradle") version "3.9.2"
}

group = "org.automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.10.0")
    testImplementation("org.junit.platform:junit-platform-console:1.10.0")

    // Gatling dependencies
    testImplementation("io.gatling.highcharts:gatling-charts-highcharts:3.9.2")
    testImplementation("io.gatling:gatling-core:3.9.2")

    // Add Scala library
    implementation("org.scala-lang:scala-library:2.13.12")

    // Cucumber JVM dependencies
    testImplementation("io.cucumber:cucumber-java:7.20.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.20.0")

    // Rest Assured for API testing
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:json-path:5.3.0")
    testImplementation("io.rest-assured:xml-path:5.3.0")

    // Optional: Allure for reporting
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.19.0")

    // Optional: Assertions library
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.test {
    useJUnitPlatform()  // Ensures JUnit 5 is used to run tests
    systemProperty("cucumber.features", "src/test/resources/features")
    systemProperty("cucumber.glue", "steps")
}
