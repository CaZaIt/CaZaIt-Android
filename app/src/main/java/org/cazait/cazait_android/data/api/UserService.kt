package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.dto.cafe.Cafes
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("/signup")
    fun postSignUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @POST("/login")
    fun doLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/cafes/{cafeId}/favourites")
    fun addToFavourite(@Path("cafeId") cafeId: String): Boolean

    @DELETE("/cafes/{cafeId}/favourites")
    fun removeFromFavourite(@Path("cafeId") cafeId: String): Boolean

    @GET("/cafes")
    fun getCafes(): Cafes
}