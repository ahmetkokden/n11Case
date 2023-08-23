package com.ng.n11case.domain.usecase

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.base.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserDetailUseCase {
    suspend fun getUserDetail(
        userName: String
    ): Flow<NetworkResult<UserDetailItem>>
}