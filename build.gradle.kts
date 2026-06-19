plugins {
    `java-library`
    id("com.gradleup.shadow") version("9.3.1")
    id("xyz.jpenilla.run-paper") version("3.0.2")
}

group = "org.lushplugins"
version = "1.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
    maven("https://repo.lushplugins.org/snapshots/") // LushLib
    maven("https://repo.fancyinnovations.com/releases") // FancyNPCs
    maven("https://repo.codemc.io/repository/maven-releases/") // PacketEvents
}

dependencies {
    // Dependencies
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.70-stable")
    compileOnly("de.oliver:FancyNpcs:2.9.2")

    // Soft Dependencies
    compileOnly("com.github.retrooper:packetevents-spigot:2.12.2")

    // Libraries
    implementation("org.lushplugins:LushLib:1.0.0")
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.17")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-rc.17")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))

    registerFeature("optional") {
        usingSourceSet(sourceSets["main"])
    }

    withSourcesJar()
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    shadowJar {
        minimize()

        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    processResources{
        filesMatching("plugin.yml") {
            expand(project.properties)
        }

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }

    runServer {
        minecraftVersion("1.21.11")

        downloadPlugins {
            modrinth("fancynpcs", "2.9.2.337")
            modrinth("packetevents", "2.11.2+spigot")
            modrinth("viaversion", "5.7.1")
            modrinth("viabackwards", "5.7.1")
        }
    }
}
