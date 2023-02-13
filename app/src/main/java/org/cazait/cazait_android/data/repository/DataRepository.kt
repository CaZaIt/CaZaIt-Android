package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.request.ReviewEditRequest
import org.cazait.cazait_android.data.model.remote.request.ReviewRequest
import org.cazait.cazait_android.data.model.remote.response.*

interface DataRepository {
    suspend fun getCafes(userId: Long, query: CafeListRequest): Flow<Resource<CafeListResponse>>
    suspend fun getMenus(cafeId: Long): Flow<Resource<MenuResponse>>
    suspend fun getReviews(cafeId: Long, query: ReviewRequest): Flow<Resource<ReviewResponse>>
    suspend fun getInterestCafes(userId: Long): Flow<Resource<InterestCafesResponse>>
    suspend fun postReview(
        userId: Long,
        cafeId: Long,
        body: ReviewEditRequest
    ): Flow<Resource<ReviewEditResponse>>
    suspend fun postInterestCafe(userId: Long, cafeId: Long): Flow<Resource<PostInterestCafeResponse>>
    suspend fun deleteInterestCafe(favoritesId: Long): Flow<Resource<DeleteInterestCafeResponse>>
}