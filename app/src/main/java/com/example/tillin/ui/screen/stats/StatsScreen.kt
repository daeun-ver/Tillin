package com.example.tillin.ui.screen.stats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tillin.ui.screen.stats.component.StatsTabBar
import com.example.tillin.ui.screen.stats.month.MonthTab
import com.example.tillin.ui.screen.stats.week.WeekTab
import com.example.tillin.ui.theme.Dimens
import java.time.LocalDate

enum class StatsTab(val label: String) {
    WEEK("주간"), MONTH("월간")
}

@Composable
fun StatsScreen() {
    var statsSelectedTab by remember() { mutableStateOf(StatsTab.WEEK) }
    var todayDate by remember { mutableStateOf(LocalDate.now()) }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
            ) {

                when (statsSelectedTab) {
                    StatsTab.WEEK -> WeekTab(date = todayDate, onDateChange = { todayDate = it })
                    StatsTab.MONTH -> MonthTab()
                }
            }
        }
        StatsTabBar(
            selected = statsSelectedTab,
            onSelected = { statsSelectedTab = it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.Nano)
        )
    }
}