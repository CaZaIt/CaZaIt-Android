package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.dto.cafe.Cafes
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import retrofit2.Call

interface DataRepository {
    fun postSignUp(body: SignUpRequest): Call<SignUpResponse>
    suspend fun postLogin(body: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(cafeId: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(cafeId: String): Flow<Resource<Boolean>>
    suspend fun getCafes(): Flow<Cafes>
}