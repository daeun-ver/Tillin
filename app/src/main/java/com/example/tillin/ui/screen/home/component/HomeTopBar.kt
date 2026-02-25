package com.example.tillin.ui.screen.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tillin.R
import com.example.tillin.ui.screen.home.HomeTab
import com.example.tillin.ui.theme.AppTextStyle
import com.example.tillin.ui.theme.Black
import com.example.tillin.ui.theme.Dimens
import com.example.tillin.ui.theme.LocalIsDarkMode
import com.example.tillin.ui.theme.LocalThemeHandler
import com.example.tillin.ui.theme.White

@Composable
fun HomeTopBar(
    selected: HomeTab,
    onSelected: (HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val isDarkMode = LocalIsDarkMode.current
    val onThemeToggle = LocalThemeHandler.current

    Box {
        ScrollableTabRow(
            selectedTabIndex = HomeTab.entries.indexOf(selected),
            modifier = modifier,
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            edgePadding = Dimens.Small,
            indicator = { tabPosition ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(
                        tabPosition[HomeTab.entries.indexOf(
                            selected
                        )]
                    ),
                    color = MaterialTheme.colorScheme.primary,
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

        //테마 변경
        IconButton(
            onClick = { onThemeToggle() },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(
                    if (isDarkMode) { R.drawable.ic_sun }
                    else { R.drawable.ic_moon }),
                contentDescription = "테마 전환",
                tint = if (isDarkMode) White else Black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}