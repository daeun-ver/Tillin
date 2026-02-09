package com.example.tillin.ui.screen.til.create

import com.example.tillin.data.local.entity.TilEntity

data class TilCreateState(
    val isLoading: Boolean = false,
    val til: TilEntity? = null,
    val error: String? = null,
    val success: Boolean = false
)