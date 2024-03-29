package org.cazait.data.repository

import kotlinx.coroutines.flow.*
import org.cazait.data.Resource
import org.cazait.data.model.local.LocalData
import org.cazait.data.model.remote.datasource.UserRemoteData
import org.cazait.data.model.remote.request.LoginRequest
import org.cazait.data.model.remote.request.SignUpRequest
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.data.model.remote.response.SignUpResponse
import org.cazait.data.model.remote.response.TokenResponse
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

    // `postToken`으로 인해 앞 단계에 UseCase 추가 하면 더 좋을 것 같음
    override suspend fun postToken(): Flow<Resource<TokenResponse>> {
        val tokens = fetchTokenInDataStore().first()
        val userId = fetchUserIdInDataStore().first()

        return flow {
            emit(
                remoteData.postToken(
                    userId = userId,
                    accessTokenHeader = mapOf("X-ACCESS-TOKEN" to tokens.first()),
                    refreshTokenHeader = mapOf("REFRESH-TOKEN" to tokens.last()),
                ),
            )
        }.flowOn(ioDispatcher)
    }

    override suspend fun logout() {
        clearDataStore()
    }

    private suspend fun clearDataStore() {
        localData.clearDataStore()
    }
}
