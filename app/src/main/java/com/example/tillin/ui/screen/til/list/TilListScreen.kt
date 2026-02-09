package com.example.tillin.ui.screen.til.list

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tillin.ui.screen.til.component.TilCard
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryBackground
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TilListScreen(
    onCreate: () -> Unit,
    onDetail: () -> Unit
) {
    val viewModel: TilListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val timeFormat = SimpleDateFormat("yyyy-MM-dd")
    val tilGroup = state.tils.groupBy { timeFormat.format(Date(it.createdAt)) }
    Scaffold(
        modifier = Modifier,
        containerColor = PrimaryBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCreate() },
                containerColor = PrimaryColor,
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
                    val topSpace = if (index == 0) Dimens.Nano else Dimens.XXLarge
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
                items(tils) { til ->
                    TilCard(
                        onClick = { onDetail() },
                        emotion = til.emotion,
                        title = til.title
                    )
                    Spacer(
                        modifier = Modifier.height(Dimens.Tiny)
                    )
                }
            }
        }
    }
}