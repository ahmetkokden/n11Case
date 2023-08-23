package com.ng.n11case.domain.repository

import com.ng.n11case.data.model.UserDetailItem
import com.ng.n11case.data.model.UserItem
import com.ng.n11case.db.github.GithubDatabase
import com.ng.n11case.db.github.TotalCountEntity
import com.ng.n11case.domain.mapper.toUserDetailEntity
import com.ng.n11case.domain.mapper.toUserDetailItem
import com.ng.n11case.domain.mapper.toUserItem
import com.ng.n11case.domain.mapper.toUserListEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDatabaseRepository @Inject internal constructor(
    private val githubDatabase: GithubDatabase
) {
    private val totalCountDao = githubDatabase.totalCountDao()


    fun getTotalCount(): Double = totalCountDao.getTotalCount().totalCount

    fun deleteTotalCount() = totalCountDao.deleteTotalCount()

    fun setTotalCount(totalCount: Double) = totalCountDao.setTotalCountEntity(
        TotalCountEntity(
            id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
            totalCount = totalCount
        )
    )

    private val userDetailDao = githubDatabase.userDetailDao()

    fun getUserDetail(): UserDetailItem = userDetailDao.getUserDetail().toUserDetailItem()

    fun deleteUserDetail() = userDetailDao.deleteUserDetail()

    fun setUserDetailEntity(userDetailItem: UserDetailItem) =
        userDetailDao.setUserDetailEntity(userDetailItem.toUserDetailEntity())

    private val userListDao = githubDatabase.userListDao()

    fun getUserList(): List<UserItem> = userListDao.flowUsers().map { entity ->
        entity.toUserItem()
    }

    fun getUserByName(userName: String): UserItem? = userListDao.findByName(userName)?.toUserItem()

    fun deleteUserList() = userListDao.purgeAllUsers()

    fun addUser(userItem: UserItem) = userListDao.addUser(userItem.toUserListEntity())

    fun addUserList(userList: List<UserItem>) =
        userListDao.addUserList(userList.map { item -> item.toUserListEntity() })
}