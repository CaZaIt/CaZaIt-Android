package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.data.Resource
import org.cazait.data.model.remote.request.CafeListRequest
import org.cazait.data.model.remote.request.ReviewEditRequest
import org.cazait.data.model.remote.request.ReviewRequest
import org.cazait.data.model.remote.response.CafeListResponse
import org.cazait.data.model.remote.response.DeleteInterestCafeResponse
import org.cazait.data.model.remote.response.InterestCafesResponse
import org.cazait.data.model.remote.response.MenuResponse
import org.cazait.data.model.remote.response.PostInterestCafeResponse
import org.cazait.data.model.remote.response.ReviewEditResponse
import org.cazait.data.model.remote.response.ReviewResponse

interface DataRepository {
    suspend fun getCafes(userId: Long, query: CafeListRequest): Flow<Resource<CafeListResponse>>
    suspend fun getMenus(cafeId: Long): Flow<Resource<MenuResponse>>
    suspend fun getReviews(cafeId: Long, query: ReviewRequest): Flow<Resource<ReviewResponse>>
    suspend fun getInterestCafes(userId: Long): Flow<Resource<InterestCafesResponse>>
    suspend fun postReview(
        userId: Long,
        cafeId: Long,
        body: ReviewEditRequest,
    ): Flow<Resource<ReviewEditResponse>>

    suspend fun postInterestCafe(
        userId: Long,
        cafeId: Long,
    ): Flow<Resource<PostInterestCafeResponse>>

    suspend fun deleteInterestCafe(favoritesId: Long): Flow<Resource<DeleteInterestCafeResponse>>
}
