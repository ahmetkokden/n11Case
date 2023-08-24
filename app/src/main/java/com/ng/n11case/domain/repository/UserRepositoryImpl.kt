package com.ng.n11case.domain.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ng.n11case.data.GithubService
import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserList
import com.ng.n11case.data.model.base.BaseErrorResponse
import com.ng.n11case.data.model.base.NetworkResult
import com.ng.n11case.domain.mapper.toUserDetailItem
import com.ng.n11case.domain.mapper.toUserList
import com.ng.n11case.utilities.Util.Companion.stringSuspending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : UserRepository {
    override suspend fun searchUsers(
        searchKeyword: String,
        page: Int,
        per_page: Int
    ): Flow<NetworkResult<UserList>> {
        return flow {
            try {
                val response = githubService.searchUsers(searchKeyword, page, per_page)
                val mappedData = response.toUserList()
                emit(NetworkResult.success(mappedData))
            } catch (e: HttpException) {
                val type = object : TypeToken<BaseErrorResponse>() {}.type
                val errorResponse: BaseErrorResponse =
                    Gson().fromJson(e.response()?.errorBody()?.stringSuspending(), type)
                emit(NetworkResult.error(errorResponse.message, errorResponse.code))
            }
        }.flowOn(Dispatchers.IO)
            .onStart { emit(NetworkResult.loading()) }
    }

    override suspend fun getUserDetail(userName: String): Flow<NetworkResult<UserDetailItem>> {
        return flow {
            try {
                val response = githubService.getUserDetail(userName)
                val mappedData = response.toUserDetailItem()
                emit(NetworkResult.success(mappedData))
            } catch (e: HttpException) {
                val type = object : TypeToken<BaseErrorResponse>() {}.type
                val errorResponse: BaseErrorResponse =
                    Gson().fromJson(e.response()?.errorBody()?.stringSuspending(), type)
                emit(NetworkResult.error(errorResponse.message, errorResponse.code))
            }
        }.flowOn(Dispatchers.IO)
            .onStart { emit(NetworkResult.loading()) }
    }
}