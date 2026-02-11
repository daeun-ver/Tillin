package com.example.tillin.ui.screen.til.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.BuildConfig
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.remote.Message
import com.example.tillin.data.remote.OpenAIRequest
import com.example.tillin.data.remote.OpenAIService
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilCreateViewModel @Inject constructor(
    private val repository: TilRepository,
    private val openAIService: OpenAIService
) : ViewModel() {
    private val _state = MutableStateFlow(TilCreateState())
    val state: StateFlow<TilCreateState> = _state.asStateFlow()

    fun loadTil(id: Long) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val til = repository.getTilById(id)
                _state.value = _state.value.copy(
                    isLoading = false,
                    title = til?.title ?: "",
                    learned = til?.learned ?: "",
                    tomorrow = til?.tomorrow ?: "",
                    error = null,
                    success = true
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message,
                    success = false
                )
            }
        }
    }

    fun saveTil(
        til: TilEntity,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val prompt = """
                    당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용을 분석해 JSON으로 응답해 주세요.
            
                    [TIL 내용]
                    제목: ${til.title}
                    오늘 배운 것: ${til.learned}
                    어려웠던 점: ${til.difficulty ?: "없음"}
                    내일 할 일: ${til.tomorrow ?: "없음"}
            
                    [분석 요청]
                    {
                        "emotion": "성취감/만족/평범/어려움/좌절 중 하나",
                        "emotionScore": 1-5 사이 정수,
                        "difficultyLevel": "쉬움/보통/어려움/매우 어려움 중 하나",
                        "comment": "격려나 조언 한 문장 (20자 이내)"
                    }
                    """.trimIndent()

                val request = OpenAIRequest(
                    messages = listOf(Message(role = "user", content = prompt))
                )

                val response = openAIService.analyzeTil(
                    token = "Bearer ${BuildConfig.OPENAI_API_KEY}",
                    request = request
                )

                val content = response.choices[0].message.content
                val json = org.json.JSONObject(content)

                val finalTil = til.copy(
                    emotion = json.getString("emotion"),
                    emotionScore = json.getInt("emotionScore"),
                    difficultyLevel = json.getString("difficultyLevel"),
                    comment = json.getString("comment")
                )

                if (til.id == 0L) {
                    repository.insertTil(finalTil)
                } else {
                    repository.updateTil(finalTil)
                }


                _state.value = _state.value.copy(success = true)
                onSuccess()
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