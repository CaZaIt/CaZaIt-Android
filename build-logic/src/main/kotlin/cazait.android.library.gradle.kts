import org.cazait.configureCoroutineAndroid
import org.cazait.configureHiltAndroid
import org.cazait.configureKotest
import org.cazait.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
