package com.example.tillin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tillin.data.local.entity.TilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonthlyDao {
    @Query("SELECT * FROM weeklystats WHERE year = :year AND month = :month AND week = :week")
    fun getMonthlyTil(year: Int, month: Int, week: Int): Flow<List<TilEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonthlyTil(til: TilEntity): Long

    @Update
    suspend fun updateMonthlyTil(til: TilEntity)
}