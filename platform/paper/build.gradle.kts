plugins {
    id ("commerce.platform-conventions")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(projects.commerceApi)
    implementation(projects.commerceCore)
    compileOnly(libs.platform.paper)
}

tasks {
    processResources {
        filesMatching(listOf("paper-plugin.yml")) {
            expand("projectVersion" to project.version)
        }
    }
}
