package com.example.tillin.ui.screen.stats.month

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.ui.screen.stats.component.KeywordCard
import com.example.tillin.ui.screen.stats.month.component.AverageDifficultyCard
import com.example.tillin.ui.screen.stats.month.component.EmotionInsightCard
import com.example.tillin.ui.screen.stats.month.component.MonthAdviceCard
import com.example.tillin.ui.screen.stats.month.component.MonthEmotionChart
import com.example.tillin.ui.screen.stats.month.component.MonthGrowthCard
import com.example.tillin.ui.screen.stats.month.component.MonthSummaryCard
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Black
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryBackground
import java.time.LocalDate
import java.time.Period

@Composable
fun MonthTab(
    date: LocalDate,
    onDateChange: (LocalDate) -> Unit
) {
    val scrollState = rememberScrollState()
    val viewModel: MonthTabViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(date) {
        viewModel.loadMonthStats(date)
    }

    val summary = state.monthlyStats?.monthlySummary ?: "데이터가 부족 합니다."
    val keyword = state.monthlyStats?.monthlyKeywords ?: "데이터가 부족 합니다."

    val bestDay = state.monthlyStats?.bestDay ?: "0월 00일"
    val worstDay = state.monthlyStats?.worstDay ?: "0월 00일"
    val growth = state.monthlyStats?.growth ?: "데이터가 부족 합니다."
    val advice = state.monthlyStats?.advice ?: "데이터가 부족 합니다."


    val year = date.year
    val month = date.monthValue

    Scaffold(
        modifier = Modifier,
        containerColor = PrimaryBackground,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Nano)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    IconButton(onClick = {
                        onDateChange(date.minus(Period.ofMonths(1)))
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Black
                        )
                    }
                }

                Row(modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "${year}년 ${month}월", style = AppTextStyle.Title)
                }

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    IconButton(onClick = {
                        onDateChange(date.plus(Period.ofMonths(1)))
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

            MonthSummaryCard(summary)
            MonthEmotionChart(state.til)
            KeywordCard(keyword)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                AverageDifficultyCard(difficulty = "어려움", modifier = Modifier.weight(1f))
                EmotionInsightCard(
                    bestDay = bestDay,
                    worstDay = worstDay,
                    modifier = Modifier.weight(1f)
                )
            }
            MonthGrowthCard(growth)
            MonthAdviceCard(advice)
        }
    }
}