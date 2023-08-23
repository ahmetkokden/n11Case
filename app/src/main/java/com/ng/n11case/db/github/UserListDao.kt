package com.ng.n11case.db.github

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserListDao {
    @Query("SELECT * FROM user_list_table")
    fun flowUsers(): List<UserListEntity>

    @Query("SELECT * FROM user_list_table where user_name LIKE  :userName LIMIT 1")
    fun findByName(userName: String?): UserListEntity?

    @Query("DELETE FROM user_list_table")
    fun purgeAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userListEntity: UserListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserList(userListEntities: List<UserListEntity>)
}