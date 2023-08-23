package com.ng.n11case.domain.mapper

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.data.model.UserList
import com.ng.n11case.domain.model.response.SearchUsersResponse
import com.ng.n11case.domain.model.response.UserDetailResponse
import com.ng.n11case.domain.model.response.UserResponse


fun SearchUsersResponse.toUserList() = UserList(
    users = items.map { userResponse ->
                userResponse.toUserItem()
    },
    totalCount = total_count
)

fun UserResponse.toUserItem() = UserItem(
    userName = login,
    userId = id,
    profileImage = avatar_url,
    userType = type,
    score = score,
    userUrl = url
)

fun UserDetailResponse.toUserDetailItem() = UserDetailItem(
    userName = login,
    userId = id,
    profileImage = avatar_url,
    userType = type,
    score = score,
    userUrl = url,
    followers = followers,
    following = following,
    publicRepoCount = public_repos
)