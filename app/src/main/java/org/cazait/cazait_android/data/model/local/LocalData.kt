package org.cazait.cazait_android.data.model.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.cazait.cazait_android.*
import java.io.IOException
import javax.inject.Inject

val Context.tokenDataStore by preferencesDataStore(TOKEN_DATASTORE)
private val Context.loginCheckDataStore by preferencesDataStore(LOGIN_CHECK_DATASTORE)
private val Context.userDataStore by preferencesDataStore(USER_DATASTORE)

class LocalData @Inject constructor(val context: Context) {

    suspend fun fetchTokenInDataStore(): Flow<List<String>> {
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

    suspend fun fetchUserIdInDataStore(): Flow<Long> {
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

    fun isLoggedIn(): Flow<Boolean> {
        return context.loginCheckDataStore.data.map { prefs ->
            prefs[LOGIN_CHECK] ?: false
        }
    }

    suspend fun saveToken(token: List<String>) {
        context.tokenDataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token.first()
            prefs[REFRESH_TOKEN] = token.last()
        }

        context.loginCheckDataStore.edit { prefs ->
            prefs[LOGIN_CHECK] = true
        }
    }

    suspend fun saveEmail(email: String) {
        context.userDataStore.edit { prefs ->
            prefs[EMAIL] = email
        }
    }

    suspend fun saveUserId(id: Long) {
        context.userDataStore.edit {prefs ->
            prefs[USER_ID] = id
        }
    }

    suspend fun clearDataStore() {
        context.tokenDataStore.edit { prefs ->
            prefs.clear()
        }
    }
}

