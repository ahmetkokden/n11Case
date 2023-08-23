package com.ng.n11case.domain.mapper

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.db.github.UserDetailEntity
import com.ng.n11case.db.github.UserListEntity
import java.util.*

fun UserDetailEntity.toUserDetailItem() = UserDetailItem(
    userName = userName,
    userId = userId,
    profileImage = profileImage,
    userType = userType,
    score = score,
    userUrl = userUrl,
    followers = followers,
    following = following,
    publicRepoCount = publicRepoCount
)

fun UserDetailItem.toUserDetailEntity() = UserDetailEntity(
    id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
    userName = userName,
    userId = userId,
    profileImage = profileImage,
    userType = userType,
    score = score,
    userUrl = userUrl,
    followers = followers,
    following = following,
    publicRepoCount = publicRepoCount
)

fun UserListEntity.toUserItem() = UserItem(
    userName = userName,
    userId = userId,
    profileImage = profileImage,
    userType = userType,
    score = score,
    userUrl = userUrl,
    isFavourited = isFavourited
)

fun UserItem.toUserListEntity() = UserListEntity(
    id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
    userName = userName,
    userId = userId,
    profileImage = profileImage,
    userType = userType,
    score = score,
    userUrl = userUrl,
    isFavourited = isFavourited
)