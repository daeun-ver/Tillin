package com.example.tillin.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,             // 메인 컬러
    secondary = DarkPrimaryTenthColor,      // 서브 컬러
    background = DarkPrimaryBackground,     // 메인 배경 컬러
    surfaceBright = DarkSecondBackground,   // 서브 배경 컬러
    surfaceVariant = DarkTopBarColor,       // 앱바 컬러
    surface = DarkCardColor,                // 카드 색상
    surfaceContainer = DarkLightColor,      // 카드 서브 색상
    onSurface = White                       // 글자 색상
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryTenthColor,
    background = PrimaryBackground,
    surfaceBright = LightGray,
    surfaceVariant = White,
    surface = White,
    surfaceContainer = Gray,
    onSurface = Black

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val LocalThemeHandler = staticCompositionLocalOf { {} }
val LocalIsDarkMode = compositionLocalOf { false }

@Composable
fun TillinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}