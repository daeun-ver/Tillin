package com.example.tillin.ui.screen.stats.month

import com.example.tillin.data.local.entity.MonthlyStatsEntity
import com.example.tillin.data.local.entity.TilEntity

data class MonthTabState(
    val til: List<TilEntity> = emptyList(),
    val monthlyStats: MonthlyStatsEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)
