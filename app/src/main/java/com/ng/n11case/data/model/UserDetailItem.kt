package com.ng.n11case.data.model

data class UserDetailItem(
    val userName: String,
    val userId: Double,
    val profileImage: String,
    val userType: String,
    val score: Float,
    val userUrl: String,
    val followers: Double,
    val following: Double,
    val publicRepoCount: Int
)
