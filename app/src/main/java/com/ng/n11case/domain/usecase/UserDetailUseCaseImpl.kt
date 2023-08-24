package com.ng.n11case.domain.usecase

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.base.NetworkResult
import com.ng.n11case.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserDetailUseCase {
    override suspend fun getUserDetail(userName: String): Flow<NetworkResult<UserDetailItem>> =
        userRepository.getUserDetail(userName)
}