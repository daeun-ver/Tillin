package com.example.tillin.ui.screen.til.create

import android.widget.Toast
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Black
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TilCreateScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    editTil: TilEntity? = null
) {
    val scrollState = rememberScrollState()
    val viewModel: TilCreateViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

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
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                    navigationIcon = {
                        IconButton(
                            onClick = { onDone() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "뒤로가기",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = {
                                val finalTil = TilEntity(
                                    id = editTil?.id ?: 0L,
                                    title = title,
                                    learned = learned,
                                    difficulty = difficulty,
                                    tomorrow = tomorrow,
                                    emotion = null,
                                    comment = null,
                                    createdAt = editTil?.createdAt ?: LocalDate.now()
                                )
                                viewModel.saveTil(
                                    onSuccess = { onDone() },
                                    til = finalTil,
                                    onError = { error ->
                                        Toast.makeText(context, "오류 발생. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_LONG).show()
                                    }
                                )
                            }
                        ) {
                            Text(
                                text = "저장",
                                modifier = Modifier.padding(horizontal = Dimens.Tiny),
                                color = MaterialTheme.colorScheme.primary
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
                onValueChange = { title = it },
                placeholder = { Text("제목", style = AppTextStyle.TitleSmall.copy(color = Gray)) },
                textStyle = AppTextStyle.TitleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Tiny),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(Dimens.Small))

            //배운 것
            Text(
                text = "배운 것",
                style = AppTextStyle.BodyTitle,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = learned,
                onValueChange = { learned = it },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            //어려운 점
            Text(
                text = "어려운 점",
                style = AppTextStyle.BodyTitle,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = difficulty,
                onValueChange = { difficulty = it },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(Dimens.XLarge))

            //내일 할 일
            Text(
                text = "내일 할 일",
                style = AppTextStyle.BodyTitle,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.XLarge, vertical = Dimens.Nano)
            )
            OutlinedTextField(
                value = tomorrow,
                onValueChange = { tomorrow = it },
                minLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                shape = RoundedCornerShape(Dimens.DefaultCornerRadius),
                textStyle = AppTextStyle.Body,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,

                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }

        if (state.isLoading) {
            Surface(
                color = Black.copy(alpha = 0.3f),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = PrimaryColor)
                        Spacer(modifier = Modifier.height(Dimens.Small))
                        Text(
                            text = "TIL 분석 중...",
                            style = AppTextStyle.Body.copy(color = White)
                        )
                    }
                }
            }
        }
    }
}