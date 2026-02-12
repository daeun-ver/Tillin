package com.example.tillin.ui.screen.til.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilListViewModel @Inject constructor(
    private val tilRepository: TilRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TilListState())
    val state: StateFlow<TilListState> = tilRepository.getAllTils()
        .map { list ->
            TilListState(tils = list, isLoading = false)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TilListState(isLoading = true)
        )

    fun deleteTil(til: TilEntity) {
        viewModelScope.launch {
            tilRepository.deleteTil(til)
        }
    }
}