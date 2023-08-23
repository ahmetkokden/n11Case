package com.ng.n11case.db.github

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TotalCountDao {
    @Query("SELECT * FROM total_count_table LIMIT 1")
    fun getTotalCount(): TotalCountEntity

    @Query("DELETE FROM total_count_table")
    fun deleteTotalCount()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTotalCountEntity(totalCountEntity: TotalCountEntity)
}