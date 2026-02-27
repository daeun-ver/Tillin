package com.example.tillin.ui.screen.til.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TilCreateViewModel @Inject constructor(
    private val tilRepository: TilRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TilCreateState())
    val state = _state.asStateFlow()

    private fun emotionToScore(emotion: String?): Int {
        return when (emotion) {
            "성취감" -> 5
            "만족" -> 4
            "평범" -> 3
            "어려움" -> 2
            "좌절" -> 1
            else -> 0
        }
    }

    fun saveTil(
        til: TilEntity,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val finalTil = til.copy(
                    emotionScore = emotionToScore(til.emotion)
                )
                if (finalTil.id == 0L) {
                    tilRepository.insertTil(finalTil)
                } else {
                    tilRepository.updateTil(finalTil)
                }

                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(success = true)
                    onSuccess()
                    Log.e("TILLIN_DEBUG", "저장성공")
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message,
                    success = false
                )
                onError(e)
                if (e is retrofit2.HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    Log.e("TILLIN_DEBUG", "OpenRouter 응답: $errorBody")
                }
                Log.e("TILLIN_DEBUG", "에러 발생!!! : ${e.message}")
            }
        }
    }
}
