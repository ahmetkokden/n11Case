package com.ng.n11case.domain.model.response

import java.io.Serializable

data class SearchUsersResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserResponse>
) : Serializable
