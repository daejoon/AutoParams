import org.gradle.api.tasks.javadoc.Javadoc

plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

dependencies {
    testImplementation(project(":autoparams"))
    testImplementation(project(":autoparams-spring"))
    testImplementation(project(":autoparams-lombok"))
    testImplementation(project(":autoparams-mockito"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.named<Javadoc>("javadoc") {
    enabled = false
}

tasks.test {
    dependsOn("checkstyleMain", "checkstyleTest")
    useJUnitPlatform()
}
