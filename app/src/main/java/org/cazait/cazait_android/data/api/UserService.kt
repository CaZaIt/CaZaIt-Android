package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import org.cazait.cazait_android.data.model.remote.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("/api/users/sign-up")
    fun postSignUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>
    @POST("/api/users/log-in")
    fun postLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>
    @POST("/cafes/{cafeId}/favourites")
    fun addToFavourite(@Path("cafeId") cafeId: String): Boolean
    @POST("/api/users/refresh")
    fun postRefreshToken(@HeaderMap headers: Map<String, String>): Call<TokenResponse>
    @DELETE("/cafes/{cafeId}/favourites")
    fun removeFromFavourite(@Path("cafeId") cafeId: String): Boolean
}