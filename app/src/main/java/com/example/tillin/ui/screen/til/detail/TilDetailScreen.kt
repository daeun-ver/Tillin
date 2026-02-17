package com.example.tillin.ui.screen.til.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.ui.screen.til.component.DifficultyTag
import com.example.tillin.ui.screen.til.component.EmotionTag
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.PrimaryBackground
import com.example.tillin.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TilDetailScreen(
    tilId: Long,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val viewModel: TilDetailViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(tilId) {
        viewModel.loadTil(tilId)
    }

    var emotion = state?.emotion
    var difficultyLevel = state?.difficultyLevel
    var comment by remember { mutableStateOf(state?.comment.orEmpty()) }
    var title by remember { mutableStateOf(state?.title.orEmpty()) }
    var learned by remember { mutableStateOf(state?.learned.orEmpty()) }
    var difficulty by remember { mutableStateOf(state?.difficulty.orEmpty()) }
    var tomorrow by remember { mutableStateOf(state?.tomorrow.orEmpty()) }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 2.dp) {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(White),
                    navigationIcon = {
                        IconButton(onClick = { onBack() }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
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
            // 제목
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(75.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = title, style = AppTextStyle.TitleSmall)
            }

            HorizontalDivider(thickness = 1.dp, color = Gray)
            Spacer(modifier = Modifier.height(Dimens.Small))

            // AI 요약
            Row {
                Text(
                    text = "AI 요약",
                    style = AppTextStyle.BodyTitle,
                    modifier = Modifier.padding(
                        start = Dimens.XLarge,
                        end = Dimens.Small,
                        top = Dimens.Nano,
                        bottom = Dimens.Nano
                    )
                )
                EmotionTag(emotion)
                Spacer(modifier = Modifier.width(Dimens.Tiny))
                DifficultyTag(difficultyLevel)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(60.dp)
                    .background(
                        color = PrimaryBackground,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
            ) {
                Text(
                    text = comment,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(Dimens.Small)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            // 배운 것
            Text(
                text = "배운 것",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(60.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
                    .border(
                        width = 1.dp,
                        color = Gray,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
            ) {
                Text(
                    text = learned,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(Dimens.Small)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            // 어려운 점
            Text(
                text = "어려운 점",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(60.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
                    .border(
                        width = 1.dp,
                        color = Gray,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
            ) {
                Text(
                    text = difficulty,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(Dimens.Small)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            // 내일 할 일
            Text(
                text = "내일 할 일",
                style = AppTextStyle.BodyTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(60.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
                    .border(
                        width = 1.dp,
                        color = Gray,
                        shape = RoundedCornerShape(Dimens.DefaultCornerRadius)
                    )
            ) {
                Text(
                    text = tomorrow,
                    style = AppTextStyle.Body,
                    modifier = Modifier.padding(Dimens.Small)
                )
            }
        }
    }
}