package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weeklystats")
data class MonthlyStatsEntity (
    @PrimaryKey(autoGenerate = true)
    val year: Int = 0,
    val month: Int = 0,
    val emotionDistribution: Map<String, Int>,
    val topKeywords: List<String>,
    val bestDay: String? = "",
    val worstDay: String? = "",
    val updateAt: Long? = null
)