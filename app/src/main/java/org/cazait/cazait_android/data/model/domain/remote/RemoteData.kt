package org.cazait.cazait_android.data.model.domain.remote

import org.cazait.cazait_android.network.NetworkConnectivity
import javax.inject.Inject

class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {

}
