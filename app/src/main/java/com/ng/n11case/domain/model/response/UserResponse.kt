package com.ng.n11case.domain.model.response

import java.io.Serializable

data class UserResponse(
    val login: String,
    val id: Double,
    val avatar_url: String,
    val type: String,
    val score: Float,
    val url: String
) : Serializable
