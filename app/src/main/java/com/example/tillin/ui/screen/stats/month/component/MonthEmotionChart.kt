package com.example.tillin.ui.screen.stats.month.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.screen.EmotionToEmoji
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.White
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun MonthEmotionChart(
    tils: List<TilEntity>,
    modifier: Modifier = Modifier
) {
    val emotionCounts = tils
        .mapNotNull { it.emotion }
        .groupingBy { it }
        .eachCount()

    val emotions = listOf("성취감", "만족", "평범", "어려움", "좌절")
    val counts = emotions.map { emotionCounts[it] ?: 0 }
    val chartEntryModel = entryModelOf(
        counts.mapIndexed { index, count ->
            entryOf(index.toFloat(), count.toFloat())
        }
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.Tiny),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(White),
        border = BorderStroke(1.dp, Gray)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.Small)
        ) {
            Text(text = "이번 달 감정 분석", style = AppTextStyle.BodySmall)
            Spacer(modifier = Modifier.height(Dimens.Tiny))
            Chart(
                chart = columnChart(),
                model = chartEntryModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Tiny),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = { value, _ ->
                        emotions.getOrNull(value.toInt()) ?: ""
                    }
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                emotions.forEachIndexed { index, emotion ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = EmotionToEmoji(emotion), style = AppTextStyle.BodySmall)
                        Spacer(modifier = Modifier.width(Dimens.Nano))
                        Text(text = "${counts[index]}개", style = AppTextStyle.BodySmall)
                    }
                }
            }
        }
    }
}