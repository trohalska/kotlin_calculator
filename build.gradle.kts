import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
}

group = "com.example"
version = "0.1"
val kotestVersion = "4.6.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
}

sourceSets {
    getByName("main").java.srcDirs("main")
    getByName("test").java.srcDirs("test")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        useIR = true
        jvmTarget = "1.8"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}