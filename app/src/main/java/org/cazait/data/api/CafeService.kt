package org.cazait.data.api

import org.cazait.data.model.remote.response.*
import org.cazait.data.model.remote.request.ReviewEditRequest
import org.cazait.data.model.remote.response.CafeListResponse
import org.cazait.data.model.remote.response.DeleteInterestCafeResponse
import org.cazait.data.model.remote.response.InterestCafesResponse
import org.cazait.data.model.remote.response.MenuResponse
import org.cazait.data.model.remote.response.PostInterestCafeResponse
import org.cazait.data.model.remote.response.ReviewEditResponse
import org.cazait.data.model.remote.response.ReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface CafeService {
    /**
     * 근처 카페들에 대한 정보를 불러온다.
     */
    @GET("/api/cafes/all/user/{userId}")
    fun getCafes(
        @Path("userId") userId: Long,
        @Query("longitude") longitude: String,
        @Query("latitude") latitude: String,
        @Query("sort") sort: String,
        @Query("limit") limit: String,
    ): Call<CafeListResponse>

    @GET("/api/menus/cafe/{cafeId}")
    fun getMenus(
        @Path("cafeId") cafeId: Long,
    ): Call<MenuResponse>

    @GET("/api/reviews/{cafeId}/all")
    fun getReviews(
        @Path("cafeId") cafeId: Long,
        @Query("sortBy") sortBy: String,
    ): Call<ReviewResponse>

    @GET("/api/favorites/user/{userId}")
    fun getInterestCafes(
        @Path("userId") userId: Long,
    ): Call<InterestCafesResponse>

    @POST("/api/reviews/user/{userId}/cafe/{cafeId}")
    fun postReview(
        @Path("userId") userId: Long,
        @Path("cafeId") cafeId: Long,
        @Body reviewEditRequest: ReviewEditRequest,
    ): Call<ReviewEditResponse>

    @POST("/api/favorites/user/{userId}/cafe/{cafeId}")
    fun postInterestCafe(
        @Path("userId") userId: Long,
        @Path("cafeId") cafeId: Long,
    ): Call<PostInterestCafeResponse>

    @DELETE("/api/favorites/delete/{favoritesId}")
    fun deleteInterestCafe(
        @Path("favoritesId") cafeId: Long,
    ): Call<DeleteInterestCafeResponse>
}
