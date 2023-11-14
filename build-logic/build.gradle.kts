plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "aidoc.android.hilt"
            implementationClass = "org.cazait.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "aidoc.kotlin.hilt"
            implementationClass = "org.cazait.HiltKotlinPlugin"
        }
    }
}
