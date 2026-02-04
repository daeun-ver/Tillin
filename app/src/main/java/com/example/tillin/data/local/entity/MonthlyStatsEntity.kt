package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weeklystats")
data class MonthlyStatsEntity (
    @PrimaryKey(autoGenerate = true)
    val year: Int = 0,                  // 년도
    val month: Int = 0,                 // 월
    val topKeywords: List<String>,      // 월간 키워드
    val bestDay: String? = "",          // 제일 즐거웠던 날
    val worstDay: String? = "",         // 제일 힘들었던 날
    val updateAt: Long? = null          // 수정 일시
)