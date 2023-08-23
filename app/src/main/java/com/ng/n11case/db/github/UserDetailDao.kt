package com.ng.n11case.db.github

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDetailDao {
    @Query("SELECT * FROM user_detail_table LIMIT 1")
    fun getUserDetail(): UserDetailEntity

    @Query("DELETE FROM user_detail_table")
    fun deleteUserDetail()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUserDetailEntity(userDetailEntity: UserDetailEntity)
}