plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencies {
    // impl project
    implementation(projects.igloomallApplication)

    // impl dependency
    implementationDependencies(Libraries.SpringBoot)
    implementationDependencies(Libraries.Database)
    implementationDependencies(Libraries.Querydsl)
    implementationDependencies(Libraries.Test)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}