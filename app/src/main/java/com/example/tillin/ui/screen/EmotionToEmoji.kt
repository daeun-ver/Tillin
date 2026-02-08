package com.example.tillin.ui.screen

fun EmotionToEmoji (emotion: String?): String {
    return when(emotion) {
        "성취감" -> "😍"
        "만족" -> "😊"
        "평범" -> "😐"
        "어려움" -> "😓"
        "좌절" -> "😭"
        else -> "🤔"
    }
}