package com.example.tillin.ui.screen.stats.week

import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity

data class WeekTabState (
    val til: List<TilEntity> = emptyList(),
    val weeklyStats: WeeklyStatsEntity? = null,
    val isLoading: Boolean = false,
    val error:String? = null,
    val success: Boolean = false
)