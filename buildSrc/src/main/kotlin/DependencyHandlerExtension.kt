import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementationDependencies(libraries: Libraries) {
    libraries.dependencies().forEach { (dependency, type) ->
        add(type.originalName, dependency)
    }
}