package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.dto.login.LoginRequest
import org.cazait.cazait_android.data.dto.login.LoginResponse

interface DataRepositorySource {
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(cafeId: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(cafeId: String): Flow<Resource<Boolean>>
}