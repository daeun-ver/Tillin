package com.example.tillin.ui.screen.stats.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.stats.StatsTab
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.Gray

@Composable
fun StatsTabBar(
    selected: StatsTab,
    onSelected: (StatsTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
    {
        StatsTab.entries.forEachIndexed { index, tab ->
            Box(
                modifier = Modifier
                    .padding(Dimens.Nano)
                    .clip(RoundedCornerShape(Dimens.TabBarCornerRadius))
                    .border(
                        width = 1.dp,
                        color = Gray,
                        shape = RoundedCornerShape(Dimens.TabBarCornerRadius)
                    )
                    .clickable { onSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab.label,
                    style = AppTextStyle.BodySmallGray,
                    modifier = Modifier
                        .padding(vertical = Dimens.Nano, horizontal = Dimens.Medium)
                )
            }
        }
    }

}