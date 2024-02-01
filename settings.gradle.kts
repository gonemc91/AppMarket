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
include(":features")
include(":features:sign-in")
include(":navigation")
include(":features:sign-up")
include(":features:catalog")
include(":features:orders")
include(":features:cart")
include(":features:cart")
include(":features:profile")
