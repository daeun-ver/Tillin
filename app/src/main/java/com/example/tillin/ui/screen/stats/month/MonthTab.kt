package com.example.tillin.ui.screen.stats.month

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MonthTab() {
    Scaffold() { padding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(padding)) {
            Text("MonthScreen")
        }
    }
}