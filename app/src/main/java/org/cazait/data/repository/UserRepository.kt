package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.data.Resource
import org.cazait.data.model.remote.request.LoginRequest
import org.cazait.data.model.remote.request.SignUpRequest
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.data.model.remote.response.SignUpResponse
import org.cazait.data.model.remote.response.TokenResponse

interface UserRepository {
    suspend fun signUp(body: SignUpRequest): Flow<Resource<SignUpResponse>>
    suspend fun login(body: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun isLoggedIn(): Flow<Boolean>
    suspend fun saveToken(token: List<String>)
    suspend fun saveEmail(email: String)
    suspend fun saveUserId(id: Long)
    suspend fun fetchTokenInDataStore(): Flow<List<String>>
    suspend fun fetchUserIdInDataStore(): Flow<Long>
    suspend fun postToken(): Flow<Resource<TokenResponse>>
    suspend fun logout()
}
