package com.example.tillin.data.repository

import com.example.tillin.data.local.TilDao
import com.example.tillin.data.local.entity.TilEntity
import javax.inject.Inject

class TilRepository @Inject constructor(
    private val tilDao: TilDao
) {
    fun getAllTils() = tilDao.getAllTils()

    suspend fun getTilById(id: Long) = tilDao.getTilById(id)

    fun getTilsForList(startTime: Long, endTime: Long) = tilDao.getTilsForList(startTime, endTime)

    suspend fun insertTil(til: TilEntity) = tilDao.insertTil(til)

    suspend fun updateTil(id: Long, til: TilEntity) = tilDao.updateTil(til)

    suspend fun deleteTil(id: Long, til: TilEntity) = tilDao.deleteTil(til)
}