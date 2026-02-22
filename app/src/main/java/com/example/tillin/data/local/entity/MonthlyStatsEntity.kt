package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "monthlystats")
data class MonthlyStatsEntity(
    @PrimaryKey
    val monthOfDay: LocalDate,          // 시작 일
    val monthlySummary: String,         // 이번 달 요약
    val monthlyKeywords: String,        // 월간 키워드
    val growth: String,                 // 성장 포인트
    val advice: String,                 // 다음 달 추천
    val bestDay: String? = "",          // 제일 즐거웠던 날
    val worstDay: String? = "",         // 제일 힘들었던 날
    val updateAt: Long? = null          // 수정 일시
)