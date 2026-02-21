package com.example.tillin.data.repository

import com.example.tillin.data.local.StatsDao
import com.example.tillin.data.local.TilDao
import com.example.tillin.data.local.entity.MonthlyStatsEntity
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity
import com.example.tillin.data.remote.OpenAIService
import com.example.tillin.data.remote.analyzeMonthlyStats
import com.example.tillin.data.remote.analyzeWeeklyStats
import kotlinx.coroutines.flow.first
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class StatsRepository @Inject constructor(
    private val statsDao: StatsDao,
    private val tilDao: TilDao,
    private val apiService: OpenAIService
) {
    //주간 통계
    suspend fun getWeeklyDataForUI(sundayMillis: Long): WeeklyData {
        val saturdayMillis = sundayMillis + (7 * 24 * 60 * 60 * 1000L) - 1

        val tils = tilDao.getTilsForStats(sundayMillis, saturdayMillis).first()
        val stats = statsDao.getWeeklyStats(sundayMillis)
        return WeeklyData(tils, stats)
    }

    suspend fun updateWeeklyStats(dateTimestamp: Long) {
        val date = Instant.ofEpochMilli(dateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()

        val monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val startMillis = monday.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val sunday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        val endMillis =
            sunday.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val weeklyTils = tilDao.getTilsForStats(startMillis, endMillis).first()

        if (weeklyTils.isNotEmpty()) {
            val summaryResult = analyzeWeeklyStats(apiService, weeklyTils)

            val newWeeklyStats = WeeklyStatsEntity(
                weekOfDay = startMillis,
                weeklySummary = summaryResult.weeklySummary,
                weeklyKeywords = summaryResult.weeklyKeywords
            )

            statsDao.insertWeeklyStats(newWeeklyStats)
        }
    }

    data class WeeklyData(
        val tils: List<TilEntity>,
        val stats: WeeklyStatsEntity?
    )

    //월간 통계
    suspend fun getMonthlyDataForUI(monthMillis: Long): MonthlyData {
        val date = Instant.ofEpochMilli(monthMillis).atZone(ZoneId.systemDefault()).toLocalDate()
        val lastDay = date.with(TemporalAdjusters.lastDayOfMonth())
        val endMillis = lastDay.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val tils = tilDao.getTilsForStats(monthMillis, endMillis).first()
        val stats = statsDao.getMonthlyStats(monthMillis)
        return MonthlyData(tils, stats)
    }

    suspend fun insertMonthlyStats(stats: MonthlyStatsEntity) = statsDao.insertMonthlyStats(stats)
    suspend fun updateMonthlyStats(dateTimestamp: Long) {
        val date = Instant.ofEpochMilli(dateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()

        val firstDay = date.with(TemporalAdjusters.firstDayOfMonth())
        val startMillis = firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val lastDay = date.with(TemporalAdjusters.lastDayOfMonth())
        val endMillis =
            lastDay.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val monthlyTils = tilDao.getTilsForStats(startMillis, endMillis).first()

        if (monthlyTils.isNotEmpty()) {
            val summaryResult = analyzeMonthlyStats(apiService, monthlyTils)

            val newMonthlyStats = MonthlyStatsEntity(
                monthOfDay = startMillis,
                monthlySummary = summaryResult.monthlySummary,
                monthlyKeywords = summaryResult.monthlyKeywords,
                growth = summaryResult.growth,
                advice = summaryResult.advice,
                averageDifficulty = summaryResult.averageDifficulty,
                bestDay = summaryResult.bestDay,
                worstDay = summaryResult.worstDay
            )
            statsDao.insertMonthlyStats(newMonthlyStats)
        }
    }

    data class MonthlyData(
        val tils: List<TilEntity>,
        val stats: MonthlyStatsEntity?
    )
}