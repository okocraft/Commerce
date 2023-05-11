pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "Commerce"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val subProjectPrefix = rootProject.name.lowercase(java.util.Locale.ENGLISH)

sequenceOf(
    "api",
    "core"
).forEach {
    include("$subProjectPrefix-$it")
    project(":$subProjectPrefix-$it").projectDir = file(it)
}

sequenceOf(
    "paper"
).forEach {
    include("$subProjectPrefix-platform-$it")
    project(":$subProjectPrefix-platform-$it").projectDir = file("./platform/$it")
}
