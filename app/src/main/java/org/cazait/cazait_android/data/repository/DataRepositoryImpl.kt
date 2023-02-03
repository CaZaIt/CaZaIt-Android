package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.api.CafeService
import org.cazait.cazait_android.data.api.UserService
import org.cazait.cazait_android.data.dto.cafe.Cafes
import org.cazait.cazait_android.data.error.DEFAULT_ERROR
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.local.LocalData
import org.cazait.cazait_android.data.model.remote.datasource.UserRemoteData
import org.cazait.cazait_android.data.model.remote.datasource.UserRemoteDataSource
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepository {
    override fun postSignUp(body: SignUpRequest): Call<SignUpResponse> = remoteData.postSignUp(body)

    override suspend fun postLogin(body: LoginRequest): Flow<Resource<LoginResponse>>{
        return flow {
            emit(remoteData.postLogin(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavourite(cafeId: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCafes(): Flow<Cafes> {
        return getCafes()
    }
}