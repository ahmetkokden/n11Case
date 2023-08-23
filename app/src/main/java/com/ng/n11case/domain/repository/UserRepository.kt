package com.ng.n11case.domain.repository

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserList
import com.ng.n11case.data.model.base.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun searchUsers(searchKeyword: String,
                            page: Int,
                            per_page: Int = 10) : Flow<NetworkResult<UserList>>
    suspend fun getUserDetail(
        userName: String
    ): Flow<NetworkResult<UserDetailItem>>
}