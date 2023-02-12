package org.cazait.cazait_android.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.datasource.CafeInfoRemoteData
import org.cazait.cazait_android.data.model.remote.datasource.CafeRemoteData
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl @Inject constructor(
    private val remoteData: CafeRemoteData,
    private val infoRemoteData: CafeInfoRemoteData,
    private val ioDispatcher: CoroutineContext,
    @ApplicationContext private val context: Context
) : DataRepository {

    override suspend fun getCafes(userId: Long, query: CafeListRequest): Flow<Resource<CafeListResponse>> {
        return flow {
            emit(remoteData.getCafeList(userId, query))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getMenus(
        cafeId: Long
    ): Flow<Resource<MenuResponse>> {
        return flow {
            emit(infoRemoteData.getMenus(cafeId))

    override suspend fun getInterestCafes(userId: Long): Flow<Resource<InterestCafesResponse>> {
        return flow<Resource<InterestCafesResponse>> {
            emit(remoteData.getInterestCafes(userId))
        }.flowOn(ioDispatcher)
    }
}