package com.ng.n11case.data.model

data class UserItem(
    val userName: String,
    val userId: Double,
    val profileImage: String,
    val userType: String,
    val score: Float,
    val userUrl: String,
    var isFavourited: Boolean = false
)