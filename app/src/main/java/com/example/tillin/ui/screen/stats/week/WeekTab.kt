package com.example.tillin.ui.screen.stats.week

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.screen.stats.component.KeywordCard
import com.example.tillin.ui.screen.stats.week.component.WeekEmotionChart
import com.example.tillin.ui.screen.stats.week.component.WeekSummaryCard
import com.example.tillin.ui.theme.PrimaryBackground

@Composable
fun WeekTab() {
    val dummy = listOf(TilEntity(title = "1", id = 1234, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 5, createdAt = 1717171200000L),
    TilEntity(title = "2", id = 2345, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 3, createdAt = 1717141200000L ),
    TilEntity(title = "3", id = 3456, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 1, createdAt = 1717084800000L ),
    )

    Scaffold(
        modifier = Modifier,
        containerColor = PrimaryBackground,
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            WeekSummaryCard("굿굿ㄱ숛굿")
            WeekEmotionChart(dummy)
            KeywordCard("코틀린")
        }
    }
}