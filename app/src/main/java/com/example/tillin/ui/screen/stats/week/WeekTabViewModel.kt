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
import com.example.tillin.data.repository.StatsRepository
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class WeekTabViewModel @Inject constructor(
    private val tilRepository: TilRepository,
    private val statsRepository: StatsRepository,
    private val openAIService: OpenAIService
) : ViewModel() {
    private val _state = MutableStateFlow(WeekTabState())
    val state: StateFlow<WeekTabState> = _state.asStateFlow()

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
            val isCurrentWeek = range.contains(LocalDate.now())

            val sundayMillis = sunday
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
            val saturdayMillis = saturday
                .plusDays(1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli() - 1

            val existingStats = statsRepository.getWeeklyStats(sundayMillis)


            if (existingStats != null && !isCurrentWeek) {
                //지난주면 로드
                _state.value = _state.value.copy(
                    weeklyStats = existingStats,
                    isLoading = false
                )
            } else {
                //이번주면 갱신
                tilRepository.getTilsForStats(startTime = sundayMillis, endTime = saturdayMillis)
                    .collect { tilList ->
                        _state.value = _state.value.copy(til = tilList)
                        if (tilList.isNotEmpty()) {
                            saveWeekStats(til = tilList, startDay = sunday)
                        }

                    }
            }

        } catch (e: Exception) {
            Log.d("loadWeekTil", "오류 발생 : ${e.message}")
        }
    }

    fun saveWeekStats(
        til: List<TilEntity>,
        startDay: LocalDate,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val startDayMillis = startDay
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()

                val weeklyData = til.joinToString("\n\n") {
                    "날짜: ${it.createdAt}, 제목: ${it.title}, 내용: ${it.learned}"
                }

                val prompt = """
                    당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용들을 분석해 JSON으로 응답해 주세요.
                    
                    [TIL 내용]
                    $weeklyData

                    [분석 요청]
                    {
                        "weeklySummary": "이번 주 TIL 요약과 격려 (20자 이내)",
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

                val finalStats = WeeklyStatsEntity(
                    weekOfDay = startDayMillis,
                    weeklySummary = json.getString("weeklySummary"),
                    weeklyKeywords = json.getString("weeklyKeywords")
                )

                statsRepository.insertWeeklyStats(finalStats)

                _state.value = _state.value.copy(
                    isLoading = false,
                    success = true,
                    weeklyStats = finalStats
                )
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