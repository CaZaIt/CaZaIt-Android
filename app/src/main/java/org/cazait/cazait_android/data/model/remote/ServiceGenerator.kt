package org.cazait.cazait_android.data.model.remote

import android.content.Context
import androidx.datastore.preferences.core.emptyPreferences
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.cazait.cazait_android.BuildConfig
import org.cazait.cazait_android.baseURL
import org.cazait.cazait_android.data.model.local.tokenDataStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val accessToken = "Authorization"
private const val timeoutConnect = 30   //In seconds

@Singleton
class ServiceGenerator @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit
    private val gson = GsonBuilder().setLenient().create()

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val jwtToken = runBlocking {
            "Bearer " + fetchJwtToken()
        }

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .header(accessToken, jwtToken)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private suspend fun fetchJwtToken(): String {
        val tokenFlow = fetchTokenInDataStore()
        val tokenList = tokenFlow.first()

        return if(tokenList.isEmpty()) ""
        else tokenList.first()
    }

    private suspend fun fetchTokenInDataStore(): Flow<List<String>> {
        return context.tokenDataStore.data.catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { prefs ->
            prefs.asMap().values.toList().map {
                it.toString()
            }
        }
    }
}