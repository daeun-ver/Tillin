package com.example.tillin.ui.screen.stats.week.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.White

@Composable
fun WeekSummaryCard (
    summary: String
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Tiny),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(White)
    ){
        Column (
            modifier = Modifier
                .padding(Dimens.Large)
        ){
            Text(
                text = "이번 주 요약",
                style = AppTextStyle.BodySmall
            )
            Spacer(modifier = Modifier.height(Dimens.Tiny))
            Text(
                text = summary,
                style = AppTextStyle.Body,
                modifier = Modifier.padding(start = Dimens.Tiny)
            )
        }
    }
}