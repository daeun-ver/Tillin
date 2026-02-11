package com.example.tillin.ui.screen.til.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilDetailViewModel @Inject constructor(
    private val repository: TilRepository
) : ViewModel() {

    private val _state = MutableStateFlow<TilEntity?>(null)
    val state: StateFlow<TilEntity?> = _state.asStateFlow()

    fun loadTil(id: Long) {
        viewModelScope.launch {
            _state.value = repository.getTilById(id)
        }
    }
}
