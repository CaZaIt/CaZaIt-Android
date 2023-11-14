package org.cazait.data.model.remote.datasource

import org.cazait.data.Resource
import org.cazait.data.model.remote.request.LoginRequest
import org.cazait.data.model.remote.request.SignUpRequest
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.data.model.remote.response.SignUpResponse
import org.cazait.data.model.remote.response.TokenResponse

interface UserRemoteDataSource {
    fun postSignUp(body: SignUpRequest): Resource<SignUpResponse>
    fun postLogin(body: LoginRequest): Resource<LoginResponse>
    fun postToken(
        userId: Long,
        accessTokenHeader: Map<String, String>,
        refreshTokenHeader: Map<String, String>,
    ): Resource<TokenResponse>
}
