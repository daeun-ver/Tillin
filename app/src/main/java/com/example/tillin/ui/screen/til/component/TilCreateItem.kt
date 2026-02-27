package com.example.tillin.ui.screen.til.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens

@Composable
fun TilCreateItem(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            style = AppTextStyle.BodyTitle,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            minLines = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Medium),
            shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
            textStyle = AppTextStyle.Body,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,

                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(modifier = Modifier.height(Dimens.XLarge))
    }
}