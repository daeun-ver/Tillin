package com.example.tillin.ui.screen.stats.week

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.BuildConfig
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.local.entity.WeeklyStatsEntity
import com.example.tillin.data.remote.ChatRequest
import com.example.tillin.data.remote.Message
import com.example.tillin.data.remote.OpenAIService
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeekTabViewModel @Inject constructor(
    private val repository: TilRepository,
    private val openAIService: OpenAIService
) : ViewModel() {
    private val _state = MutableStateFlow(WeekTabState())

    fun getWeekRange(date: LocalDate): List<LocalDate> {
        val dayOfWeek = date.dayOfWeek.value % 7
        val sunday = date.minusDays(dayOfWeek.toLong())
        return (0..6).map { sunday.plusDays(it.toLong()) }
    }

    fun loadWeekStats(date: LocalDate) = viewModelScope.launch {
        try {
            val range = getWeekRange(date)
            val sunday = range.first()
            val saturday = range.last()

            val weeklyTil = repository.getTilsForStats(startTime = sunday, endTime = saturday)

        } catch (e: Exception) {
            Log.d("loadWeekTil", "오류 발생 : ${e.message}")
        }
    }

    fun saveWeekStats(
        til: TilEntity,
        stats: WeeklyStatsEntity,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val prompt = """
                    당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용들을 분석해 JSON으로 응답해 주세요.
                    
                    [TIL 내용]
                    제목: ${til.title}
                    오늘 배운 것: ${til.learned}
                    어려웠던 점: ${til.difficulty ?: "없음"}
                    내일 할 일: ${til.tomorrow ?: "없음"}

                    [분석 요청]
                    {
                        "weeklyComment": "이번 주 TIL 요약과 격려 (20자 이내)",
                        "weeklyKeywords": "이번 주 TIL 주요 키워드 (3개)",
                    }
                    
                """.trimIndent()

                val response = openAIService.analyzeTil(
                    auth = "Bearer ${BuildConfig.OPENAI_API_KEY}",
                    request = ChatRequest(
                        messages = listOf(
                            Message("user", prompt)
                        )
                    )
                )

                val content = response.choices[0].message.content
                val startIndex = content.indexOf("{")
                val endIndex = content.lastIndexOf("}")

                val clean = if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                    content.substring(startIndex, endIndex + 1)
                } else {
                    content.replace("```json", "").replace("```", "").trim()
                }

                val json = JSONObject(clean)

                val finalStats = stats.copy(
                    weeklyComment = json.getString("weeklyComment"),
                    weeklyKeywords = json.getString("weeklyKeywords")
                )

                //repository.insertTil(finalStats)

                _state.value = _state.value.copy(success = true)
                onSuccess()
                Log.e("TILLIN_DEBUG", "저장성공")

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message,
                    success = false
                )
                onError(e)
            }
        }
    }
}