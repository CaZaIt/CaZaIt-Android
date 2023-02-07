package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.dto.cafe.Cafes
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import retrofit2.Call
import retrofit2.http.*

interface CafeService {
    /**
     * 근처 카페들에 대한 정보를 불러온다.
     */
    @GET("/api/cafes/all/user/{userId}")
    fun getCafes(@Body cafeListRequest: CafeListRequest): Call<CafeListResponse>
}