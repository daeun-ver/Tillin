package com.example.tillin.data.repository

import com.example.tillin.data.local.StatsDao
import com.example.tillin.data.local.entity.MonthlyStatsEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity
import javax.inject.Inject

class StatsRepository @Inject constructor(
    private val statsDao: StatsDao
) {
    fun getAllStats() = statsDao.getAllStats()

    //주간 통계
    suspend fun getWeeklyStats(startTime: Long) = statsDao.getWeeklyStats(startTime)

    suspend fun insertWeeklyStats(stats: WeeklyStatsEntity) = statsDao.insertWeeklyStats(stats)

    //월간 통계
    suspend fun getMonthlyStats(startTime: Long) = statsDao.getMonthlyStats(startTime)

    suspend fun insertMonthlyStats(stats: MonthlyStatsEntity) = statsDao.insertMonthlyStats(stats)
}