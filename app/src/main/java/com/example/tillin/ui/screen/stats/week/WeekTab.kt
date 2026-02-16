package com.example.tillin.ui.screen.stats.week

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.screen.stats.component.KeywordCard
import com.example.tillin.ui.screen.stats.week.component.WeekEmotionChart
import com.example.tillin.ui.screen.stats.week.component.WeekSummaryCard
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Black
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryBackground
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Period

@Composable
fun WeekTab(
    date: LocalDate,
    onDateChange: (LocalDate) -> Unit
) {
    val dummy = listOf(TilEntity(title = "1", id = 1234, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 5, createdAt = 1717171200000L),
    TilEntity(title = "2", id = 2345, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 3, createdAt = 1717141200000L ),
    TilEntity(title = "3", id = 3456, learned = "ㅁㄴㅇㄹ", emotion = "das", emotionScore = 1, createdAt = 1717084800000L ),
    )

    val weekStart = DayOfWeek.SUNDAY

    val week = remember(date) {
        val firstOfMonth = LocalDate.of(date.year, date.monthValue, 1)
        val offset = (firstOfMonth.dayOfWeek.ordinal - weekStart.ordinal + 7) % 7
        (offset + date.dayOfMonth - 1) / 7 + 1
    }

    val year = date.year
    val month = date.monthValue

    Scaffold(
        modifier = Modifier,
        containerColor = PrimaryBackground,
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Nano)
            ){
                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    IconButton(onClick = {
                        onDateChange(date.minus(Period.ofDays(7)))
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Black
                        )
                    }
                }

                Row (modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "${year}년 ${month}월 ${week}주차", style = AppTextStyle.Title)
                }

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    IconButton(onClick = {
                        onDateChange(date.plus(Period.ofDays(7)))
                    }) {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(bottom = Dimens.Small))
            WeekSummaryCard("굿굿ㄱ숛굿")
            WeekEmotionChart(dummy)
            KeywordCard("코틀린")
        }
    }
}