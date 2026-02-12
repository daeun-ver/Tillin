package com.example.tillin.ui.screen.stats.week

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tillin.ui.theme.PrimaryBackground

@Composable
fun WeekTab() {
    Scaffold(
        modifier = Modifier,
        containerColor = PrimaryBackground,
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(padding)) {
            Text("WeekScreen")
        }
    }
}