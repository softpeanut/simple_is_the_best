plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
    implementationDependencies(Libraries.Test)
}

allOpen {
    annotation("karrotpay.assignment.igloomall.common.annotation.UseCase")
}