plugins {
    kotlin("jvm") version PluginVersions.JVM_VERSION
}

subprojects {
    group = "karrotpay.assignment"
    version = "0.0.1-SNAPSHOT"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = PluginVersions.JVM_VERSION
    }

    apply {
        plugin("org.jetbrains.kotlin.kapt")
        version = PluginVersions.KAPT_VERSION
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
        }

        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementationDependencies(Libraries.Kotlin)
        implementationDependencies(Libraries.Jackson)
    }
}
