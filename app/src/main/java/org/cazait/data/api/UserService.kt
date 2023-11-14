package org.cazait.data.api

import org.cazait.data.model.remote.request.LoginRequest
import org.cazait.data.model.remote.request.SignUpRequest
import org.cazait.data.model.remote.response.IsDuplicateEmailResponse
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.data.model.remote.response.SignUpResponse
import org.cazait.data.model.remote.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    /**
     * auths
     */

    @POST("/api/auths/log-in")
    fun postLogin(
        @Query("role") role: String = "user",
        @Body loginRequest: LoginRequest,
    ): Call<LoginResponse>

    @POST("/api/auths/refresh/{userId}")
    fun postRefreshToken(
        @Path("userId") userId: Long,
        @Query("role") role: String = "user",
        @HeaderMap accessToken: Map<String, String>,
        @HeaderMap refreshToken: Map<String, String>,
    ): Call<TokenResponse>

    /**
     * users
     */

    @POST("/api/users/sign-up")
    fun postSignUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @GET("/api/users/email")
    fun getIsDuplicateEmail(
        @Query("email") email: String = "",
    ): Call<IsDuplicateEmailResponse>
}
