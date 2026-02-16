package com.example.tillin.ui.screen.stats.week.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun WeekEmotionChart(
    til: List<TilEntity>,
    modifier: Modifier = Modifier
) {
    val weekTil = til
        .filter { it.emotionScore != null }
        .takeLast(7)


    val entries = (0..6).map { index ->
        val score = til.getOrNull(index)?.emotionScore?.toFloat() ?: 0f
        entryOf(index.toFloat(), score)
    }
    val chartEntryModel = entryModelOf(entries)

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
            val days = arrayOf("일", "월", "화", "수", "목", "금", "토")

            Text(text = "이번 주 감정 분석", style = AppTextStyle.BodySmall)
            Spacer(modifier = Modifier.height(Dimens.Tiny))
            Chart(
                chart = lineChart(
                    lines = listOf(
                        lineSpec(lineColor = PrimaryColor),
                    )
                ),
                model = chartEntryModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Tiny),
                startAxis = rememberStartAxis(
                    valueFormatter = { value, _ ->
                            when (value.toInt()) {
                                1 -> "😭"
                                2 -> "😓"
                                3 -> "😐"
                                4 -> "😊"
                                5 -> "😍"
                                else -> ""
                            }

                    },
                    itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6),
                    guideline = null
                ),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = { value, _ ->
                        days.getOrElse(value.toInt()) {""}
                    },
                    guideline = null,
                    itemPlacer = AxisItemPlacer.Horizontal.default(
                        spacing = 1,
                        offset = 0,
                        shiftExtremeTicks = false
                    ),
                )
            )
        }
    }
}