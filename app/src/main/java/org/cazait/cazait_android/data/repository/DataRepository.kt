package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse

interface DataRepository {
    suspend fun getCafes(userId: Long, query: CafeListRequest): Flow<Resource<CafeListResponse>>
}