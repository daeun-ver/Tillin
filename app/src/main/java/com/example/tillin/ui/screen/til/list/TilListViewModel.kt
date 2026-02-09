package com.example.tillin.ui.screen.til.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilListViewModel @Inject constructor(
    private val tilRepository: TilRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TilListState())
    val state: StateFlow<TilListState> = _state.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadTilList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                tilRepository.getAllTils().collect { tilList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            tils = tilList
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}