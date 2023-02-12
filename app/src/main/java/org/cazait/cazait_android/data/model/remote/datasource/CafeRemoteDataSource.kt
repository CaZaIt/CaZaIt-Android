package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import org.cazait.cazait_android.data.model.remote.response.DeleteInterestCafeResponse
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import org.cazait.cazait_android.data.model.remote.response.PostInterestCafeResponse

interface CafeRemoteDataSource {
    fun getCafeList(userId: Long, query: CafeListRequest): Resource<CafeListResponse>
    fun getInterestCafes(userId: Long): Resource<InterestCafesResponse>
    fun postInterestCafe(cafeId:Long, userId: Long): Resource<PostInterestCafeResponse>
    fun deleteInterestCafe(favoritesId: Long): Resource<DeleteInterestCafeResponse>
}