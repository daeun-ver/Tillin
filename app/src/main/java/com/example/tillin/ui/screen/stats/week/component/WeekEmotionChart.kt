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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun WeekEmotionChart(
    til: List<TilEntity>,
    modifier: Modifier = Modifier
) {
    val sorted = til.sortedBy { it.createdAt }
    val weekMap = mutableMapOf<Int, Float>()

    sorted.forEach { item ->
        val date = item.createdAt

        val dayIndex = date.dayOfWeek.value % 7

        weekMap[dayIndex] = item.emotionScore?.toFloat() ?: 0f
    }

    val entries = (0..6).map { index ->
        val score = weekMap[index] ?: 0f
        entryOf(index.toFloat(), score)
    }
    val chartEntryModel = entryModelOf(entries)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.Tiny),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.Small)
        ) {
            val days = arrayOf("일", "월", "화", "수", "목", "금", "토")

            Text(
                text = "이번 주 감정 분석",
                style = AppTextStyle.BodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(Dimens.Tiny))
            Chart(
                chart = lineChart(
                    lines = listOf(
                        lineSpec(lineColor = MaterialTheme.colorScheme.primary)
                    ),
                    axisValuesOverrider = com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider.fixed(
                        minY = 1f,
                        maxY = 5f
                    )
                ),
                model = chartEntryModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Tiny),
                startAxis = rememberStartAxis(
                    axis = lineComponent(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        thickness = 1.dp
                    ),
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
                    itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 5),
                    guideline = null
                ),
                bottomAxis = rememberBottomAxis(
                    label = axisLabelComponent(MaterialTheme.colorScheme.onSurface),
                    axis = lineComponent(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        thickness = 1.dp
                    ),
                    valueFormatter = { value, _ ->
                        days.getOrElse(value.toInt()) { "" }
                    },
                    guideline = lineComponent(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        thickness = 1.dp
                    ),
                    itemPlacer = AxisItemPlacer.Horizontal.default(
                        spacing = 1,
                        offset = 0,
                        shiftExtremeTicks = false
                    )
                )
            )
        }
    }
}