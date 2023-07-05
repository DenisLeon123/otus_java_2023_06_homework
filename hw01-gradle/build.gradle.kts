plugins {
    id("java")
    id("com.github.johnrengelman.shadow")
}

group = "ru.otus"
version = "SNAPSHOT-0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.guava:guava")
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.getByName("shadowJar"))
}