package com.example.tillin.ui.screen.stats.month

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tillin.data.repository.StatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MonthTabViewModel @Inject constructor(
    private val statsRepository: StatsRepository
): ViewModel() {
    private val _state = MutableStateFlow(MonthTabState())
    val state: StateFlow<MonthTabState> = _state.asStateFlow()

    fun loadMonthStats(date: LocalDate) = viewModelScope.launch {
        try {
            val firstDayOfMonth = date.withDayOfMonth(1)

            val dataPackage = statsRepository.getMonthlyDataForUI(firstDayOfMonth)

            _state.value = _state.value.copy(til = dataPackage.tils, monthlyStats = dataPackage.stats, isLoading = false)

        } catch (e: Exception) {
            Log.d("loadMonthTil", "오류 발생 : ${e.message}")
        }
    }

    fun saveMonthStats(
        date: LocalDate,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                statsRepository.updateMonthlyStats(date)
                loadMonthStats(date)

                onSuccess()
                Log.e("TILLIN_DEBUG", "월간 통계 저장 성공")

            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message, success = false)
                onError(e)
            }
        }
    }
}