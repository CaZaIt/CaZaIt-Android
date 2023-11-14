plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.cazait"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.cazait"
        minSdk = 28
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    // Add Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.core.common)
    implementation(libs.kotlinx.coroutines.play.services)

    // okHttp
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    // lifecycle
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.databinding.runtime)
    implementation(libs.car.ui.lib)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // splash
    implementation(libs.androidx.core.splashscreen)

    // room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
    kapt("androidx.room:room-compiler:2.6.0")

    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // viewpager2
    implementation(libs.androidx.viewpager2)

    /* Third Party Library */

    // expandable layout
    implementation(libs.expandablelayout)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kaptAndroidTest(libs.hilt.android.compiler)

    // naver maps
    implementation(libs.map.sdk)

    // location request
    implementation(libs.play.services.location)

    // swipe refresh
    implementation(libs.androidx.swiperefreshlayout)

    // Moshi
    implementation(libs.moshi)
    // Gson
    implementation(libs.converter.gson)

    // Dot Indicator
    implementation(libs.dotsindicator)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}
