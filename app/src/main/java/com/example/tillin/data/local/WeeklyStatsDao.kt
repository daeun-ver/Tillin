package com.example.tillin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tillin.data.local.entity.TilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyStatsDao {
    @Query("SELECT * FROM weeklystats WHERE year = :year AND month = :month AND week = :week")
    fun getWeeklyTil(year: Int, month: Int, week: Int): Flow<List<TilEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeklyTil(til: TilEntity): Long

    @Update
    suspend fun updateWeeklyTil(til: TilEntity)
}