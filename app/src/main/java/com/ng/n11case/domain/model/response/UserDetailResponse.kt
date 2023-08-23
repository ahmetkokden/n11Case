package com.ng.n11case.domain.model.response

import java.io.Serializable

data class UserDetailResponse(
    val login: String,
    val id: Double,
    val avatar_url: String,
    val type: String,
    val score: Float,
    val url: String,
    val followers: Double,
    val following: Double,
    val public_repos: Int
) : Serializable
