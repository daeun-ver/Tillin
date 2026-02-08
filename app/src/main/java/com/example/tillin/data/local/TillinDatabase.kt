package com.example.tillin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tillin.data.local.entity.TilEntity

@Database(
    entities = [TilEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TillinDatabase : RoomDatabase() {
    abstract fun tilDao(): TilDao

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