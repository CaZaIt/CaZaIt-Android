package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import org.cazait.cazait_android.data.model.remote.response.TokenResponse
import retrofit2.Call

interface UserRemoteDataSource {
    fun postSignUp(body: SignUpRequest): Resource<SignUpResponse>
    fun postLogin(body: LoginRequest): Resource<LoginResponse>
    fun postToken(userId: Long, accessTokenHeader: Map<String, String>, refreshTokenHeader: Map<String, String>): Resource<TokenResponse>
}