package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.dto.cafe.Cafes
import retrofit2.http.*

interface CafeService {
    /**
     * 근처 카페들에 대한 정보를 불러온다.
     */
    @GET("/api/cafes/all")
    @Headers("Content-Type: application/json")
    suspend fun getCafes(
    ): Cafes
}