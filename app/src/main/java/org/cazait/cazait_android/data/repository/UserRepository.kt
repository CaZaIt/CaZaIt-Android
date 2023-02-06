package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse

interface UserRepository {

    suspend fun signUp(body: SignUpRequest): Flow<Resource<SignUpResponse>>

    suspend fun login(body: LoginRequest): Flow<Resource<LoginResponse>>

    suspend fun isLoggedIn(): Flow<Boolean>

    suspend fun saveToken(token: List<String>)

    suspend fun getToken(): Flow<List<String>>

    suspend fun clearDataStore()
}