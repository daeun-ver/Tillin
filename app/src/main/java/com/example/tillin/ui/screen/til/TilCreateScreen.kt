package com.example.tillin.ui.screen.til

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TilCreateScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    editTil: TilEntity? = null
) {
    val scrollState = rememberScrollState()
    var title by remember { mutableStateOf(editTil?.title.orEmpty()) }
    var learned by remember { mutableStateOf(editTil?.learned.orEmpty()) }
    var difficulty by remember { mutableStateOf(editTil?.difficulty.orEmpty()) }
    var tomorrow by remember { mutableStateOf(editTil?.tomorrow.orEmpty()) }

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
                                imageVector = Icons.Default.Close,
                                contentDescription = "뒤로가기"
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = { onDone() }
                        ) {
                            Text(
                                text = "저장",
                                modifier = Modifier.padding(horizontal = Dimens.Tiny),
                                color = PrimaryColor
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
            TextField(
                value = title,
                onValueChange = { title },
                placeholder = { Text("제목", style = AppTextStyle.TitleSmall.copy(color = Gray)) },
                textStyle = AppTextStyle.TitleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Tiny),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )


            HorizontalDivider(
                thickness = 1.dp,
                color = Gray
            )

            Spacer(modifier = Modifier.height(Dimens.Small))

            //배운 것
            Text(
                text = "배운 것",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = learned,
                onValueChange = { learned },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray
                )
            )

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            //어려운 점
            Text(
                text = "어려운 점",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = difficulty,
                onValueChange = { difficulty },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray
                )
            )

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            //내일 할 일
            Text(
                text = "내일 할 일",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = tomorrow,
                onValueChange = { tomorrow },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray
                )
            )
        }
    }
}