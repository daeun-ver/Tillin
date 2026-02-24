package com.example.tillin.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.example.tillin.data.local.TillinDatabase
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.White
import com.example.tillin.ui.theme.WidgetTextStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TilWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TilWidget()
}

class TilWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val todayTil = withContext(Dispatchers.IO) {
            val database = TillinDatabase.getDatabase(context)
            database.tilDao().getTodayTils()
        }
        provideContent {
            TilWidgetContent(todayTil)
        }
    }
}

@Composable
fun TilWidgetContent(til: List<TilEntity>?) {
    Box(
        modifier = GlanceModifier.fillMaxSize().background(White.copy(alpha = 0.5f))
    ) {
        Column(modifier = GlanceModifier.padding(Dimens.Small)) {
            Text(text = "오늘의 TIL", style = WidgetTextStyle.BodyTitle)
            Spacer(modifier = GlanceModifier.defaultWeight())
            if (!til.isNullOrEmpty()) {
                til.forEach { tils ->
                    Text(text = tils.title, style = WidgetTextStyle.BodyTitle)
                }
            } else {
                Text(text = "작성한 TIL이 없습니다.", style = WidgetTextStyle.Body)
            }
        }
    }
}