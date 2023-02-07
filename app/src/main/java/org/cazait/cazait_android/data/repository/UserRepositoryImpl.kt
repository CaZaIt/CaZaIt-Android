package org.cazait.cazait_android.data.repository

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import org.cazait.cazait_android.LOGIN_CHECK_DATASTORE
import org.cazait.cazait_android.TOKEN_DATASTORE
import org.cazait.cazait_android.USER_DATASTORE
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.datasource.UserRemoteData
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private val Context.tokenDataStore by preferencesDataStore(TOKEN_DATASTORE)
private val Context.loginCheckDataStore by preferencesDataStore(LOGIN_CHECK_DATASTORE)
private val Context.userDataStore by preferencesDataStore(USER_DATASTORE)

class UserRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val ioDispatcher: CoroutineContext,
    @ApplicationContext private val context: Context
) : UserRepository {

    override suspend fun clearDataStore() {
        context.tokenDataStore.edit { prefs ->
            prefs.clear()
        }
    }

    override suspend fun login(body: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteData.postLogin(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun signUp(body: SignUpRequest): Flow<Resource<SignUpResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveToken(token: List<String>) {
        context.tokenDataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token.first()
            prefs[REFRESH_TOKEN] = token.last()
        }

        context.loginCheckDataStore.edit { prefs ->
            prefs[LOGIN_CHECK] = true
        }
    }

    override suspend fun saveEmail(email: String) {
        context.userDataStore.edit { prefs ->
            prefs[EMAIL] = email
        }
    }

    override suspend fun saveUserId(id: Long) {
        context.userDataStore.edit {prefs ->
            prefs[USER_ID] = id
        }
    }

    override suspend fun getTokenInDataStore(): Flow<List<String>> {
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

    override suspend fun getUserIdInDataStore(): Flow<Long> {
        return context.userDataStore.data.catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { prefs ->
            prefs[USER_ID]!!
        }
    }

    override suspend fun isLoggedIn(): Flow<Boolean> {
        return context.loginCheckDataStore.data.map { prefs ->
            prefs[LOGIN_CHECK] ?: false
        }
    }

    private companion object PreferenceKeys {
        val EMAIL = stringPreferencesKey("email")
        val USER_ID = longPreferencesKey("user_id")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val LOGIN_CHECK = booleanPreferencesKey("login_check")
    }
}