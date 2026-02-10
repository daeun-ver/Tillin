package com.example.tillin.ui.screen.til.create

import com.example.tillin.data.local.entity.TilEntity

data class TilCreateState(
    val title: String = "",
    val learned: String = "",
    val difficulty: String = "",
    val tomorrow: String = "",

    val emotion: String = "",
    val emotionScore: Int = 0,
    val difficultyLevel: String = "",
    val comment: String = "",

    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)