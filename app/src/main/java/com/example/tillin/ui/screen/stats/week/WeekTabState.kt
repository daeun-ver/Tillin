package com.example.tillin.ui.screen.stats.week

import com.example.tillin.data.local.entity.TilEntity

data class WeekTabState (
    val til: List<TilEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error:String? = null,
    val success: Boolean = false
)