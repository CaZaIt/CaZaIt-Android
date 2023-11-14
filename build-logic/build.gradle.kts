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
            id = "cazait.android.hilt"
            implementationClass = "org.cazait.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "cazait.kotlin.hilt"
            implementationClass = "org.cazait.HiltKotlinPlugin"
        }
    }
}
