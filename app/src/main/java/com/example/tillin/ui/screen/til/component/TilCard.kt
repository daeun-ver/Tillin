package com.example.tillin.ui.screen.til.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.EmotionToEmoji
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.White

@Composable
fun TilCard(
    emotion: String?,
    title: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val emoji = EmotionToEmoji(emotion)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        }
    )
    val isDismissing = dismissState.targetValue != SwipeToDismissBoxValue.Settled

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (isDismissing) {
                            Color.Red.copy(alpha = 0.8f)
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(Dimens.TILCornerRadius)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "삭제",
                    tint = White
                )
            }
        }
    )
    {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            shape = RoundedCornerShape(Dimens.TILCornerRadius),
            colors = CardDefaults.cardColors(White),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.XLarge)
            ) {
                Text(
                    text = emoji,
                    style = AppTextStyle.TitleSmall
                )
                Box(modifier = Modifier.padding(Dimens.Nano)) { }
                Text(
                    text = title,
                    style = AppTextStyle.TitleSmall
                )
            }
        }
    }

}