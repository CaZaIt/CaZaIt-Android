package org.cazait.cazait_android.data.repository

import kotlinx.coroutines.flow.Flow
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.dto.login.LoginRequest
import org.cazait.cazait_android.data.dto.login.LoginResponse
import org.cazait.cazait_android.data.model.local.LocalData
import org.cazait.cazait_android.data.model.remote.RemoteData
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavourite(cafeId: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}