package com.example.tillin.ui.screen.til.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilCreateViewModel @Inject constructor(
    private val tilRepository: TilRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TilCreateState())
    val state = _state.asStateFlow()

    fun saveTil(
        til: TilEntity,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                if (til.id == 0L) {
                    tilRepository.insertTil(til)
                } else {
                    tilRepository.updateTil(til)
                }

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
                if (e is retrofit2.HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    Log.e("TILLIN_DEBUG", "OpenRouter 응답: $errorBody")
                }
                Log.e("TILLIN_DEBUG", "에러 발생!!! : ${e.message}")
            }
        }
    }
}
