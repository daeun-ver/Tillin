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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.ui.screen.til.component.DifficultyTag
import com.example.tillin.ui.screen.til.component.EmotionTag
import com.example.tillin.ui.screen.til.component.TilDetailItem
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens

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

    val comment = state?.comment.orEmpty()
    val title = state?.title.orEmpty()
    val learned = state?.learned.orEmpty()
    val difficulty = state?.difficulty.orEmpty()
    val tomorrow = state?.tomorrow.orEmpty()

    Scaffold(
        topBar = {
            Surface(shadowElevation = 2.dp) {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.surfaceVariant),
                    navigationIcon = {
                        IconButton(onClick = { onBack() }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "뒤로가기",
                                tint = MaterialTheme.colorScheme.onSurface
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
                .background(color = MaterialTheme.colorScheme.surfaceBright)
        ) {
            // 제목
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium)
                    .heightIn(75.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    style = AppTextStyle.TitleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
            Spacer(modifier = Modifier.height(Dimens.Small))

            TilDetailItem(
                title = "AI 요약",
                content = comment,
                containerColor = MaterialTheme.colorScheme.background,
                borderColor = MaterialTheme.colorScheme.surfaceVariant
            )
            TilDetailItem("배운 것", learned)
            TilDetailItem("어려운 점", difficulty)
            TilDetailItem("내일 할 일", tomorrow)

        }
    }
}