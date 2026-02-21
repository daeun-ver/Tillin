package com.example.tillin.data.repository

import com.example.tillin.data.local.TilDao
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.remote.OpenAIService
import com.example.tillin.data.remote.analyzeTil
import javax.inject.Inject

class TilRepository @Inject constructor(
    private val tilDao: TilDao,
    private val statsRepository: StatsRepository,
    private val apiService: OpenAIService
) {
    fun getAllTils() = tilDao.getAllTils()

    suspend fun getTilById(id: Long) = tilDao.getTilById(id)

    fun getTilsForStats(startTime: Long, endTime: Long) = tilDao.getTilsForStats(startTime, endTime)

    suspend fun insertTil(til: TilEntity) {
        val analyzedTil = analyzeTil(apiService, til)

        tilDao.insertTil(analyzedTil)

        statsRepository.updateWeeklyStats(til.createdAt)
        statsRepository.updateMonthlyStats(til.createdAt)
    }

    suspend fun updateTil(til: TilEntity) {
        tilDao.updateTil(til)
        statsRepository.updateWeeklyStats(til.createdAt)
        statsRepository.updateMonthlyStats(til.createdAt)
    }

    suspend fun deleteTil(til: TilEntity) {
        tilDao.deleteTil(til)
        statsRepository.updateWeeklyStats(til.createdAt)
        statsRepository.updateMonthlyStats(til.createdAt)
    }
}