package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse

interface CafeRemoteDataSource {
    fun getCafeList(userId: Long, body: CafeListRequest): Resource<CafeListResponse>
}