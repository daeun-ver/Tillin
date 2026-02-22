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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class StatsRepository @Inject constructor(
    private val statsDao: StatsDao,
    private val tilDao: TilDao,
    private val apiService: OpenAIService
) {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    //주간 통계
    suspend fun getWeeklyDataForUI(date: LocalDate): WeeklyData {
        val sunday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val saturday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

        val tils = tilDao.getTilsForStats(sunday, saturday).first()
        val stats = statsDao.getWeeklyStats(sunday)
        return WeeklyData(tils, stats)
    }

    suspend fun updateWeeklyStats(date: LocalDate) {
        val sunday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val saturday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

        val weeklyTils = tilDao.getTilsForStats(sunday, saturday).first()

        if (weeklyTils.isNotEmpty()) {
            val summaryResult = analyzeWeeklyStats(apiService, weeklyTils)

            val newWeeklyStats = WeeklyStatsEntity(
                weekOfDay = sunday,
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
    suspend fun getMonthlyDataForUI(date: LocalDate): MonthlyData {
        val firstDay = date.with(TemporalAdjusters.firstDayOfMonth())
        val lastDay = date.with(TemporalAdjusters.lastDayOfMonth())

        val tils = tilDao.getTilsForStats(firstDay, lastDay).first()
        val stats = statsDao.getMonthlyStats(firstDay)
        return MonthlyData(tils, stats)
    }

    suspend fun updateMonthlyStats(date: LocalDate) {
        val firstDay = date.with(TemporalAdjusters.firstDayOfMonth())
        val lastDay = date.with(TemporalAdjusters.lastDayOfMonth())

        val monthlyTils = tilDao.getTilsForStats(firstDay, lastDay).first()

        if (monthlyTils.isNotEmpty()) {
            val summaryResult = analyzeMonthlyStats(apiService, monthlyTils)

            val newMonthlyStats = MonthlyStatsEntity(
                monthOfDay = firstDay,
                monthlySummary = summaryResult.monthlySummary,
                monthlyKeywords = summaryResult.monthlyKeywords,
                growth = summaryResult.growth,
                advice = summaryResult.advice,
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