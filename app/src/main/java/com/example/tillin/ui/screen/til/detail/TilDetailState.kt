package com.example.tillin.ui.screen.til.detail

import com.example.tillin.data.local.entity.TilEntity

data class TilDetailState(
    val isLoading: Boolean = false,
    val til: TilEntity? = null,
    val error: String? = null
)
