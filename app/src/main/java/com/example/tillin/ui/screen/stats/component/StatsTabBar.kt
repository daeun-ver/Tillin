package com.example.tillin.ui.screen.stats.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.stats.StatsTab
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens

@Composable
fun StatsTabBar(
    selected: StatsTab,
    onSelected: (StatsTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(bottom = Dimens.Large)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(Dimens.TabBarCornerRadius)
            )
            .clip(RoundedCornerShape(Dimens.TabBarCornerRadius))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(Dimens.TabBarCornerRadius)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        StatsTab.entries.forEachIndexed { index, tab ->
            Box(
                modifier = Modifier
                    .padding(Dimens.Nano)
                    .clip(RoundedCornerShape(Dimens.TabBarCornerRadius))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = 1.dp,
                        color = if (selected == tab) {
                            MaterialTheme.colorScheme.surfaceContainer
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(Dimens.TabBarCornerRadius)
                    )
                    .clickable { onSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab.label,
                    style = AppTextStyle.BodyGray,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = Dimens.Nano, horizontal = Dimens.Small)
                )
            }
        }
    }
}