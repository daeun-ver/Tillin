package com.example.tillin.ui.screen.stats.month.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.EmotionToEmoji
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun EmotionInsightCard(
    bestDay: String,
    worstDay: String,
    modifier: Modifier = Modifier
) {
    val displayFormatter = DateTimeFormatter.ofPattern("MM월 dd일")

    val bestDayFormat = bestDay.let { LocalDate.parse(it).format(displayFormatter) }
    val worstDayFormat = worstDay.let { LocalDate.parse(it).format(displayFormatter) }


    Card(
        modifier = modifier
            .padding(Dimens.Tiny)
            .height(130.dp),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)

    ) {
        Column(
            modifier = Modifier.padding(Dimens.Large)
        ) {
            Text(
                text = "감정 분석",
                style = AppTextStyle.BodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(Dimens.Tiny))

            //최고 날
            Row {
                Text(
                    text = EmotionToEmoji("성취감"),
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
                Spacer(modifier = Modifier.width(Dimens.Nano))
                Text(
                    text = bestDayFormat,
                    style = AppTextStyle.Body,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.Tiny))

            //최저 날
            Row {
                Text(
                    text = EmotionToEmoji("좌절"),
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
                Spacer(modifier = Modifier.width(Dimens.Nano))
                Text(
                    text = worstDayFormat,
                    style = AppTextStyle.Body,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
            }
        }
    }
}