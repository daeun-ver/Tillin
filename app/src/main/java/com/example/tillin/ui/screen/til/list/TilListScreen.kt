package com.example.tillin.ui.screen.til.list

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.glance.appwidget.updateAll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.ui.screen.til.component.TilCard
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.White
import com.example.tillin.widget.TilWidget

@Composable
fun TilListScreen(
    onCreate: () -> Unit,
    onDetail: (Long) -> Unit
) {
    val viewModel: TilListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val tilGroup = state.tils.groupBy { it.createdAt.toString() }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            TilWidget().updateAll(context)
            Log.d("TILLIN_DEBUG", "목록 진입: 위젯 자동 갱신 성공")
        } catch (e: Exception) {
            Log.e("TILLIN_DEBUG", "위젯 갱신 실패: ${e.message}")
            }
        }

    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCreate() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = White,
                modifier = Modifier.size(60.dp),
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "추가",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.XLarge)
        ) {
            tilGroup.toList().forEachIndexed { index, (date, tils) ->
                item {
                    val topSpace = if (index == 0) Dimens.Nano else Dimens.XLarge
                    Spacer(modifier = Modifier.height(topSpace))

                    Text(
                        text = date,
                        style = AppTextStyle.BodySmallGray,
                        modifier = Modifier.padding(Dimens.Nano)
                    )
                    Spacer(
                        modifier = Modifier.height(Dimens.Tiny)
                    )
                }
                items(items = tils, key = { til -> til.id }) { til ->
                    TilCard(
                        onClick = { onDetail(til.id) },
                        emotion = til.emotion,
                        title = til.title,
                        onDelete = { viewModel.deleteTil(til, context) }
                    )
                    Spacer(
                        modifier = Modifier.height(Dimens.Tiny)
                    )
                }
            }
        }
    }
}