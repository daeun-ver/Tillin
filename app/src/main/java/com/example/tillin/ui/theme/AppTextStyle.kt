package com.example.tillin.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTextStyle {
    //타이틀 텍스트
    val TitleLarge = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    )
    val Title = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )
    val TitleSmall = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )

    //바디 텍스트
    val BodyComment = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
    val Body = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    val BodyGray = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Gray
    )
    val BodySmall = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
    val BodySmallGray = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Gray
    )
    val BodyThin = TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp
    )


    //버튼 텍스트
    val Button= TextStyle (
        fontFamily = Pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )
}