package com.example.tillin.ui.screen.home.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.tillin.ui.screen.home.HomeTab
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Black
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White

@Composable
fun HomeTopBar(
    selected: HomeTab,
    onSelected: (HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = HomeTab.entries.indexOf(selected),
        modifier = modifier,
        contentColor = Black,
        containerColor = White,
        edgePadding = Dimens.Small,
        indicator = { tabPosition ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPosition[HomeTab.entries.indexOf(selected)]),
                color = PrimaryColor,
                height = 3.dp
            )
        },
        divider = {}
    ) {
        HomeTab.entries.forEachIndexed { index, tab ->
            Tab(
                selected = selected == tab,
                onClick = { onSelected(tab) },
                modifier = Modifier.height(50.dp),
                text = {
                    Text(
                        tab.label,
                        style = if (selected == tab) AppTextStyle.BodySmall else AppTextStyle.BodySmallGray
                    )
                }
            )
        }
    }
}