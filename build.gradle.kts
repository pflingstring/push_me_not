plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "1.1.2"
    java
}

group = "com.github.pflingstring.push.me.not"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    pluginName.set(provider { "$project.name" })
    version.set("2020.3.1")
    plugins.set(listOf("git4idea"))
}

tasks {
    patchPluginXml {
        changeNotes.set("""""".trimIndent())
    }
    runPluginVerifier {
        localPaths.setFrom("")
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
