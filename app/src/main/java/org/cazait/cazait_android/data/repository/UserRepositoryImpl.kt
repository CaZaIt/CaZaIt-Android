package org.cazait.cazait_android.data.repository

import android.util.Log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.local.LocalData
import org.cazait.cazait_android.data.model.remote.datasource.UserRemoteData
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import org.cazait.cazait_android.data.model.remote.response.TokenResponse
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val localData: LocalData,
    private val ioDispatcher: CoroutineContext,
) : UserRepository {

    override suspend fun login(body: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteData.postLogin(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun signUp(body: SignUpRequest): Flow<Resource<SignUpResponse>> {
        return flow {
            emit(remoteData.postSignUp(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun saveToken(token: List<String>) {
        localData.saveToken(token)
    }

    override suspend fun saveEmail(email: String) {
        localData.saveEmail(email)
    }

    override suspend fun saveUserId(id: Long) {
        localData.saveUserId(id)
    }

    override suspend fun fetchTokenInDataStore(): Flow<List<String>> {
        return localData.fetchTokenInDataStore()
    }

    override suspend fun fetchUserIdInDataStore(): Flow<Long> {
        return localData.fetchUserIdInDataStore()
    }

    override suspend fun isLoggedIn(): Flow<Boolean> {
        return localData.isLoggedIn()
    }

    override suspend fun clearDataStore() {
        localData.clearDataStore()
    }
    // `postToken`으로 인해 앞 단계에 UseCase 추가 하면 더 좋을 것 같음
    override suspend fun postToken(refreshTokenHeader: Map<String, String>): Flow<Resource<TokenResponse>> {
        return flow {
            emit(remoteData.postToken(refreshTokenHeader))
        }.flowOn(ioDispatcher)
    }
}