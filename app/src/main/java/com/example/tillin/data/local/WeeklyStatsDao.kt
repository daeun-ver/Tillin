package com.example.tillin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tillin.data.local.entity.WeeklyStatsEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface WeeklyStatsDao {
    @Query("SELECT * FROM weeklystats WHERE weekOfDay = :weekOfDay")
    suspend fun getWeeklyStats(weekOfDay: LocalDate): WeeklyStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeklyStats(stats: WeeklyStatsEntity): Long

    @Query("SELECT * FROM weeklystats ORDER BY weekOfDay DESC")
    fun getAllWeeklyStats(): Flow<List<WeeklyStatsEntity>>
}