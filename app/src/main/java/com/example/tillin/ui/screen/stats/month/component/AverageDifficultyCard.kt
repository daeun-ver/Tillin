package com.example.tillin.ui.screen.stats.month.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.White

@Composable
fun AverageDifficultyCard(
    difficulty: String,
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
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Large)
        ) {
            Text(
                text = "평균 난이도",
                style = AppTextStyle.BodySmall
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = difficulty,
                    style = AppTextStyle.TitleLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}