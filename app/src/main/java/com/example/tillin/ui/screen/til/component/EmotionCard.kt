package com.example.tillin.ui.screen.til.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tillin.ui.screen.EmotionToEmoji
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryTenthColor

@Composable
fun EmotionCard (
    emotion: String?
) {
    val emoji = EmotionToEmoji(emotion)
    Card (
        modifier = Modifier.padding(),
        shape = RoundedCornerShape(Dimens.TILCornerRadius),
        colors = CardDefaults.cardColors(PrimaryTenthColor)
    ) {
        Text(
            text = "$emoji $emotion",
            style = AppTextStyle.BodySmall,
            modifier = Modifier.padding(vertical = Dimens.Nano, horizontal = Dimens.Tiny)
        )
    }
}