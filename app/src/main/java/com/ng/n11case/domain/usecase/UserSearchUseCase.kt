package com.ng.n11case.domain.usecase

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserList
import com.ng.n11case.data.model.base.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserSearchUseCase {
    suspend fun searchUsers(
        searchKeyword: String,
        page: Int,
        per_page: Int = 10
    ): Flow<NetworkResult<UserList>>


}