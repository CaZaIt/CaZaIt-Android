package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.api.CafeAPI
import org.cazait.cazait_android.data.dto.cafe.CafesDto
import org.cazait.cazait_android.data.model.remote.login.LoginRequest
import org.cazait.cazait_android.data.model.remote.login.LoginResponse
import org.cazait.cazait_android.data.model.local.LocalData
import org.cazait.cazait_android.data.model.remote.RemoteData
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext,
    private val retrofit: Retrofit
) : DataRepository {
    private val cafeAPI: CafeAPI = retrofit.create(CafeAPI::class.java)

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavourite(cafeId: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCafes(): Flow<CafesDto> {
        return getCafes()
    }
}