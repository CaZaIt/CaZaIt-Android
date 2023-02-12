package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.request.ReviewRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import org.cazait.cazait_android.data.model.remote.response.ReviewResponse

interface DataRepository {
    suspend fun getCafes(userId: Long, query: CafeListRequest): Flow<Resource<CafeListResponse>>
    suspend fun getMenus(cafeId:Long):Flow<Resource<MenuResponse>>
    suspend fun getReviews(cafeId:Long, query: ReviewRequest): Flow<Resource<ReviewResponse>>
    suspend fun getInterestCafes(userId: Long): Flow<Resource<InterestCafesResponse>>
}