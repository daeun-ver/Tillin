package com.example.tillin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tillin.data.local.entity.TilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TilDao {
    @Query("SELECT * FROM tils ORDER BY createdAt DESC")
    fun getAllTils(): Flow<List<TilEntity>>

    @Query("SELECT * FROM tils WHERE id = :id")
    suspend fun getTilById(id: Long): TilEntity?

    // 목록용 (최신순)
    @Query("SELECT * FROM tils WHERE createdAt >= :startTime AND createdAt < :endTime ORDER BY createdAt DESC")
    fun getTilsForList(startTime: Long, endTime: Long): Flow<List<TilEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTil(til: TilEntity): Long

    @Update
    suspend fun updateTil(til: TilEntity)

    @Delete
    suspend fun deleteTil(til: TilEntity)

    // 통계용 (과거순)
    @Query("SELECT * FROM tils WHERE createdAt >= :startTime AND createdAt < :endTime ORDER BY createdAt ASC")
    fun getTilsForStats(startTime: Long, endTime: Long): Flow<List<TilEntity>>
}