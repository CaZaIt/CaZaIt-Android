package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse

interface CafeRemoteDataSource {
    fun getCafeList(userId: Long, query: CafeListRequest): Resource<CafeListResponse>
    fun getInterestCafes(userId: Long): Resource<InterestCafesResponse>
}