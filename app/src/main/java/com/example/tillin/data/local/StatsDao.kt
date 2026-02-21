package com.example.tillin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tillin.data.local.entity.MonthlyStatsEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao {
    @Query("SELECT * FROM weeklystats ORDER BY weekOfDay DESC")
    fun getAllStats(): Flow<List<WeeklyStatsEntity>>

    //주간 통계
    @Query("SELECT * FROM weeklystats WHERE weekOfDay = :startDate")
    suspend fun getWeeklyStats(startDate: Long): WeeklyStatsEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeklyStats(stats: WeeklyStatsEntity)
    @Update
    suspend fun updateWeeklyStats(stats: WeeklyStatsEntity)

    //월간 통계
    @Query("SELECT * FROM monthlystats WHERE monthOfDay = :monthDate")
    suspend fun getMonthlyStats(monthDate: Long): MonthlyStatsEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonthlyStats(stats: MonthlyStatsEntity)
    @Update
    suspend fun updateMonthlyStats(stats: MonthlyStatsEntity)

}