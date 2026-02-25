package com.example.tillin.ui.screen.stats.component

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
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens

@Composable
fun KeywordCard(
    keyword: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.Tiny),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(Dimens.Large)
        ) {
            Text(
                text = "주요 키워드",
                style = AppTextStyle.BodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(Dimens.Tiny))
            Text(
                text = keyword,
                style = AppTextStyle.Body,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = Dimens.Tiny)
            )
        }
    }
}