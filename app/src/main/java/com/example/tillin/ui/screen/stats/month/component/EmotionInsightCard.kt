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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.EmotionToEmoji
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.White

@Composable
fun EmotionInsightCard(
    bestDay: String,
    worstDay: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(Dimens.Tiny)
            .height(130.dp),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(White),
        border = BorderStroke(1.dp, Gray)

    ) {
        Column(
            modifier = Modifier.padding(Dimens.Large)
        ) {
            Text(text = "감정 분석", style = AppTextStyle.BodySmall)
            Spacer(modifier = Modifier.height(Dimens.Tiny))

            //최고 날
            Row {
                Text(
                    text = EmotionToEmoji(bestDay),
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
                Spacer(modifier = Modifier.width(Dimens.Nano))
                Text(
                    text = bestDay,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.Tiny))

            //최저 날
            Row {
                Text(
                    text = EmotionToEmoji(worstDay),
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
                Spacer(modifier = Modifier.width(Dimens.Nano))
                Text(
                    text = worstDay,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(start = Dimens.Tiny)
                )
            }
        }
    }
}