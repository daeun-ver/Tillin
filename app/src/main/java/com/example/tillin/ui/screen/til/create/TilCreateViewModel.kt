package com.example.tillin.ui.screen.til.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
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
                val analysis = openAIService.analyzeTil(
                    title = til.title,
                    learned = til.learned,
                    difficulty = til.difficulty?.ifBlank { null },
                    tomorrow = til.tomorrow?.ifBlank { null }
                )
                val finalTil = til.copy(
                    emotion = analysis.emotion,
                    emotionScore = analysis.emotionScore,
                    difficultyLevel = analysis.difficultyLevel,
                    comment = analysis.comment
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