plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "1.1.2"
    java
}

group = "com.github.pflingstring.push.me.not"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    plugins.set(listOf("git4idea"))
    updateSinceUntilBuild.set(false)
    sameSinceUntilBuild.set(true)
}

val eapIdePath = properties["eap.ide.path"]
val stableIdePath = properties["stable.ide.path"]

tasks {
    patchPluginXml {
        sinceBuild.set("203.6682")
        changeNotes.set("""""".trimIndent())
    }
    runPluginVerifier {
        localPaths.setFrom(eapIdePath, stableIdePath)
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}