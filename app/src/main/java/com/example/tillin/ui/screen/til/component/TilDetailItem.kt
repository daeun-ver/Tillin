package com.example.tillin.ui.screen.til.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens

@Composable
fun TilDetailItem(
    title: String,
    content: String,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.surfaceContainer
) {
    Column {
        Text(
            text = title,
            style = AppTextStyle.BodyTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Medium)
                .heightIn(min = 60.dp)
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                )
        ) {
            Text(
                text = content,
                style = AppTextStyle.Body,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(Dimens.Small)
            )
        }
        Spacer(modifier = Modifier.height(Dimens.XLarge))
    }
}