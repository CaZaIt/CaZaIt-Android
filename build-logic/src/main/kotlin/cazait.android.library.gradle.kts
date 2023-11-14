import cazait.configureCoroutineAndroid
import cazait.configureHiltAndroid
import cazait.configureKotest
import cazait.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
