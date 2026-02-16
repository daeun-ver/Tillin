package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "weeklystats")
data class WeeklyStatsEntity (
    @PrimaryKey(autoGenerate = true)
    val weekOfDay: LocalDate,           // 시작 일
    val topKeywords: String,            // 주간 키워드
    val updateAt: Long? = null          // 수정 일시
)