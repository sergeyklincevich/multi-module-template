plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.klinserg.news.datastore.proto"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

//androidComponents.beforeVariants {
//    android.sourceSets.register(it.name) {
//        val buildDir = layout.buildDirectory.get().asFile
//        java.srcDir(buildDir.resolve("generated/source/proto/${it.name}/java"))
//        kotlin.srcDir(buildDir.resolve("generated/source/proto/${it.name}/kotlin"))
//    }
//}

dependencies {
    implementation(libs.protobuf.javalite)
}