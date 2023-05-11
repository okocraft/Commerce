plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.build.shadow)

    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}
