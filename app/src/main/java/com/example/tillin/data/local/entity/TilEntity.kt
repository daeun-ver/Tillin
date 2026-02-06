package com.example.tillin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tils")
data class TilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,              // 자동 생성 ID

    val title: String,                    // TIL 제목
    val learned: String,                  // 오늘 배운 것
    val difficulty: String? = null,       // 어려웠던 점
    val tomorrow: String? = null,         // 내일 할 일

    // AI 분석 결과
    val emotion: String?,          // 감정 (성취감, 만족, 평범, 어려움, 좌절)
    val emotionScore: Int? = 0,           // 감정 점수 (1-5)
    val difficultyLevel: String? = null,  // 난이도 (쉬움, 보통, 어려움, 매우 어려움)
    val comment: String? = null,          // AI 코멘트

    val createdAt: Long = System.currentTimeMillis(),   // 생성 일시
    val updatedAt: Long? = null    //수정 일시
)