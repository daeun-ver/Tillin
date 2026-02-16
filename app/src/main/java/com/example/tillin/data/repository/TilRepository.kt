package com.example.tillin.data.repository

import com.example.tillin.data.local.TilDao
import com.example.tillin.data.local.entity.TilEntity
import java.time.LocalDate
import javax.inject.Inject

class TilRepository @Inject constructor(
    private val tilDao: TilDao
) {
    fun getAllTils() = tilDao.getAllTils()

    suspend fun getTilById(id: Long) = tilDao.getTilById(id)

    fun getTilsForStats(startTime: LocalDate, endTime: LocalDate) = tilDao.getTilsForStats(startTime, endTime)

    suspend fun insertTil(til: TilEntity) = tilDao.insertTil(til)

    suspend fun updateTil(til: TilEntity) = tilDao.updateTil(til)

    suspend fun deleteTil(til: TilEntity) = tilDao.deleteTil(til)
}