package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import retrofit2.Call
import retrofit2.http.*

interface CafeService {
    /**
     * 근처 카페들에 대한 정보를 불러온다.
     */
    @GET("/api/cafes/all/user/{userId}")
    fun getCafes(
        @Path("userId") userId: Long,
        @Query("latitude") latitude: String,
        @Query("limit") limit: String,
        @Query("longitude") longitude: String,
        @Query("sort") sort: String
    ): Call<CafeListResponse>

    @POST("/api/cafes/all/user/{userId}")
    fun getCafes2(
        @Path("userId") userId: Long,
        @Body cafeListRequest: CafeListRequest
    ): Call<CafeListResponse>
}