plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-platform-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version ("1.8.0-1.0.9")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.cazait.app"
    compileSdk = 33

    defaultConfig {
        applicationId = "org.cazait.cazait"
        minSdk = 28
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    //AndroidX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // Add Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    //okHttp
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")

    //lifecycle
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.databinding:databinding-runtime:7.4.0")
    implementation("com.android.car.ui:car-ui-lib:2.3.0")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // splash
    implementation("androidx.core:core-splashscreen:1.0.0")

    //room
    implementation("androidx.room:room-ktx:2.5.0")
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")
    testImplementation("androidx.room:room-testing:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // viewpager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")


    /* Third Party Library */

    //expandable layout
    implementation("com.github.skydoves:expandablelayout:1.0.7")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //naver maps
    implementation("com.naver.maps:map-sdk:3.16.2")

    //location request
    implementation("com.google.android.gms:play-services-location:17.0.0")

    //swipe refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dot Indicator
    implementation("com.tbuonomo:dotsindicator:4.3")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    implementation("com.google.android.material:material:1.9.0-alpha01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}