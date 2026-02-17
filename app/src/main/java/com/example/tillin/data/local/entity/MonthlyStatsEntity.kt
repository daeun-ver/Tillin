package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthlystats")
data class MonthlyStatsEntity (
    @PrimaryKey
    val monthOfDay: Long,               // 시작 일
    val monthlyComment: String,         // 이번 달 요약
    val monthlyKeywords: String,        // 월간 키워드
    val bestDay: String? = "",          // 제일 즐거웠던 날
    val worstDay: String? = "",         // 제일 힘들었던 날
    val updateAt: Long? = null          // 수정 일시
)