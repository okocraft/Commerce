plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
    api(libs.annotations)
    compileOnlyApi(libs.slf4j)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

val javaVersion = JavaVersion.VERSION_17
val charset = Charsets.UTF_8

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks {
    compileJava {
        options.encoding = charset.name()
        options.release.set(javaVersion.ordinal + 1)
    }

    processResources {
        filteringCharset = charset.name()
    }

    test {
        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
