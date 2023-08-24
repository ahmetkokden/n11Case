package com.ng.n11case.domain.usecase

import com.ng.n11case.data.model.UserList
import com.ng.n11case.data.model.base.NetworkResult
import com.ng.n11case.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSearchUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserSearchUseCase {
    override suspend fun searchUsers(
        searchKeyword: String,
        page: Int,
        per_page: Int
    ): Flow<NetworkResult<UserList>> = userRepository.searchUsers(
        searchKeyword, page, per_page
    )
}