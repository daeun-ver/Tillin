package com.example.tillin.ui.screen.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.tillin.ui.screen.stats.component.StatsTabBar
import com.example.tillin.ui.screen.stats.month.MonthTab
import com.example.tillin.ui.screen.stats.week.WeekTab

enum class StatsTab(val label: String) {
    WEEK("주간"), MONTH("월간")
}

@Composable
fun StatsScreen() {
    var statsSelectedTab by remember() { mutableStateOf(StatsTab.WEEK) }
    Scaffold() { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            StatsTabBar(
                selected = statsSelectedTab,
                onSelected = { statsSelectedTab = it }
            )

            when (statsSelectedTab) {
                StatsTab.WEEK -> WeekTab()
                StatsTab.MONTH -> MonthTab()
            }
        }
    }
}