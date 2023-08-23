package com.ng.n11case.db.github

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserListEntity::class,UserDetailEntity::class,TotalCountEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase(){

    abstract fun userDetailDao(): UserDetailDao

    abstract fun userListDao(): UserListDao

    abstract fun totalCountDao(): TotalCountDao
}