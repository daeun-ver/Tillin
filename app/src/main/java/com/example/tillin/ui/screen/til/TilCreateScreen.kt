package com.example.tillin.ui.screen.til

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TilCreateScreen(
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 2.dp,
            ) {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(White),
                    navigationIcon = {
                        IconButton(onClick = { onBack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    }
                )
            }

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            Text("TilCreateScreen")
            HorizontalDivider(
                thickness = 1.dp,
                color = Gray
            )

            //배운 것
            OutlinedTextField(
                value = "a",
                onValueChange = { "abc" },
                label = { Text("배운 것") },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
            )

            //어려운 점
            OutlinedTextField(
                value = "a",
                onValueChange = { "abc" },
                label = { Text("어려운 점") },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
            )

            //내일 할 일
            OutlinedTextField(
                value = "a",
                onValueChange = { "abc" },
                label = { Text("내일 할 일") },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
            )

        }
    }
}