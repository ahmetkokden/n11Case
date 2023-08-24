package com.ng.n11case.db.github

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total_count_table")
data class TotalCountEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "total_count")
    val totalCount: Double,
    @ColumnInfo(name = "search_text")
    val searchText: String
)