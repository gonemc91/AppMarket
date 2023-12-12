pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AppMarket"
include(":app")
include(":core")
include(":core:theme")
include(":core:common")
include(":core:presentation")
include(":core:common-impl")
include(":data")
include(":core:wiring")
include(":features")
include(":features:sign-in")
include(":navigation")
