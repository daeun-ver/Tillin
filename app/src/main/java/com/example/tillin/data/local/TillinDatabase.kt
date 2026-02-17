package com.example.tillin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tillin.data.local.entity.MonthlyStatsEntity
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity

@Database(
    entities = [TilEntity::class, WeeklyStatsEntity::class, MonthlyStatsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TillinDatabase : RoomDatabase() {
    abstract fun tilDao(): TilDao
    abstract fun statsDao(): StatsDao

    companion object {
        @Volatile
        private var INSTANCE: TillinDatabase? = null

        fun getDatabase(context: Context): TillinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TillinDatabase::class.java,
                    "tillin_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}