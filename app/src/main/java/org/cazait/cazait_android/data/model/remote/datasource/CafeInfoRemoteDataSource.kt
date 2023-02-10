package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.MenuRequest
import org.cazait.cazait_android.data.model.remote.request.ReviewRequest
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.data.model.remote.response.ReviewResponse

interface CafeInfoRemoteDataSource {
    fun getMenus(cafeId: Int): Resource<MenuResponse>
    fun getReviews(body: ReviewRequest): Resource<ReviewResponse>
}