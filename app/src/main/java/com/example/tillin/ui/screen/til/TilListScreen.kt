package com.example.tillin.ui.screen.til

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryBackground
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TilListScreen(onCreate: () -> Unit) {
    val dummy = listOf(
        TilEntity(id = 1234, title = "Kotlin 공부", learned = "코틀린 문법을 공부 했다", emotion = "😊", createdAt = 1770331200000),
        TilEntity(id = 5678, title = "Room DB 정리", learned = "Room DB를 정리했다.", emotion = "😊", createdAt = 1770331100000),
        TilEntity(id = 1434, title = "과거 기록", learned = "어제 배운 것", emotion = "🤔", createdAt = 1770244800000)
    )
    val timeFormat = SimpleDateFormat("yyyy-MM-dd")
    val tilGroup = dummy.groupBy { timeFormat.format(Date(it.createdAt)) }
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
            tilGroup.forEach { (date, tils) ->
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(Dimens.Tiny)
                    )
                    Text(
                        text = date,
                        style = AppTextStyle.BodySmallGray

                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(Dimens.Nano)
                    )
                }
                items(tils) { til ->
                    TilCard(
                        emotion = til.emotion,
                        title = til.title
                    ) { }
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(Dimens.Nano)
                    )
                }
            }


        }
    }
}