import java.io.ByteArrayOutputStream
import java.nio.file.Files
import java.nio.file.Path

plugins {
    id("com.github.johnrengelman.shadow")
    id("commerce.common-conventions")
}

val ci = findProperty("commerce.ci")?.toString()?.toBoolean() ?: false

dependencies {
    implementation(project(":commerce-api"))
    implementation(project(":commerce-core"))
}

tasks {
    build {
        dependsOn(shadowJar)

        if (ci) {
            dependsOn("copyToUploadingDirectory")
        }
    }

    jar {
        var version = project.version.toString()

        if (ci && version.endsWith("-SNAPSHOT")) { // for development build in CI
            version = "${project.version}-git-${getLatestCommitHash()}"
        }

        manifest {
            attributes(
                "Implementation-Version" to version
            )
        }
    }

    shadowJar {
        val projectName = project.name
        val index = projectName.lastIndexOf('-')

        if (index == -1) {
            return@shadowJar
        }

        val rawPlatformName = projectName.substring(index + 1)
        val platformName = rawPlatformName[0].uppercase() + rawPlatformName.substring(1)

        archiveFileName.set("Commerce-$platformName-${rootProject.version}.jar")

        val groupId = project.group
        relocate("com.github.siroshun09", "$groupId.libs")
        relocate("org.jetbrains", "$groupId.libs.jetbrains")
        relocate("org.intellij", "$groupId.libs.intellij")
    }

    create("copyToUploadingDirectory") {
        dependsOn(shadowJar)

        doFirst {
            val jarFilepath = shadowJar.get().archiveFile.get().asFile.toPath()
            val targetDir = rootProject.buildDir.toPath().resolve("ci-upload")

            if (!Files.isDirectory(targetDir)) {
                Files.createDirectories(targetDir)
            }

            val targetFilepath = targetDir.resolve(jarFilepath.fileName)

            Files.deleteIfExists(targetFilepath)
            Files.copy(jarFilepath, targetFilepath)
        }
    }
}

fun getLatestCommitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short=7", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

