package com.example.tillin.ui.screen.til.list

import com.example.tillin.data.local.entity.TilEntity

data class TilListState(
    val isLoading: Boolean = false,
    val tils: List<TilEntity> = emptyList(),
    val error: String? = null
)