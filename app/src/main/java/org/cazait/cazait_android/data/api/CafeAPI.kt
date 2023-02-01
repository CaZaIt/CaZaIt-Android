package org.cazait.cazait_android.data.api

import org.cazait.cazait_android.data.dto.cafe.CafesDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CafeAPI {
    /**
     * 근처 카페들에 대한 정보를 불러온다.
     */
    @GET("/api/cafes/all")
    @Headers("Content-Type: application/json")
    fun getCafes(
    ): Call<CafesDto>

    /**
     *
     */
}