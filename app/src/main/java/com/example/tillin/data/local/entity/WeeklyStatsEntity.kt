package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weeklystats")
data class WeeklyStatsEntity (
    @PrimaryKey(autoGenerate = true)
    val year: Int,              // 년도
    val month: Int,             // 월
    val weekOfMonth: Int,       // 주차
    val dailyEmotionScores: Map<String, Int>,       // 주간 감정 점수
    val topKeywords: List<String>,                  // 주간 키워드
    val updateAt: Long? = null                      //수정 일시
)