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
include(":app")
include(":features:news-ui")
include(":news-api")
include(":news-data")
include(":news-core")
include(":news-db")
include(":news-uikit")
