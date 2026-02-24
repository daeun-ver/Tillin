package com.example.tillin.ui.screen.til.list

import android.content.Context
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.local.entity.TilEntity
import com.example.tillin.data.repository.TilRepository
import com.example.tillin.widget.TilWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TilListViewModel @Inject constructor(
    private val tilRepository: TilRepository
) : ViewModel() {
    val state: StateFlow<TilListState> = tilRepository.getAllTils()
        .map { list ->
            TilListState(tils = list, isLoading = false)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TilListState(isLoading = true)
        )

    fun deleteTil(
        til: TilEntity,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            tilRepository.deleteTil(til)
            withContext(Dispatchers.Main) {
                TilWidget().updateAll(context)
            }
        }
    }
}