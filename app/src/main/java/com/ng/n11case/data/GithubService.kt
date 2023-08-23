package com.ng.n11case.data

import com.ng.n11case.domain.model.response.SearchUsersResponse
import com.ng.n11case.domain.model.response.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") searchKeyword: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 10
    ): SearchUsersResponse

    @GET("users/{userName}")
    suspend fun getUserDetail(
        @Path("userName") userName: String
    ): UserDetailResponse
}