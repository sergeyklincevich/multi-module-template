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

rootProject.name = "NewsTemplateApp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":features:news-ui")
include(":features:news-details")
include(":features:news-search")
include(":features:news-favorite")
include(":core:bluetooth")
include(":core:datastore")
include(":core:datastore-proto")
include(":core:news-api")
include(":core:news-core")
include(":core:news-data")
include(":core:news-db")
include(":core:news-uikit")
