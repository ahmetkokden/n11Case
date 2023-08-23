package com.ng.n11case.db.github

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_detail_table"
)
class UserDetailEntity(
    @PrimaryKey()
    val id: Long,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "user_id")
    val userId: Double,
    @ColumnInfo(name = "profile_image")
    val profileImage: String,
    @ColumnInfo(name = "user_type")
    val userType: String,
    @ColumnInfo(name = "score")
    val score: Float,
    @ColumnInfo(name = "user_url")
    val userUrl: String,
    @ColumnInfo(name = "followers")
    val followers: Double,
    @ColumnInfo(name = "following")
    val following: Double,
    @ColumnInfo(name = "publicR_repo_count")
    val publicRepoCount: Int
)