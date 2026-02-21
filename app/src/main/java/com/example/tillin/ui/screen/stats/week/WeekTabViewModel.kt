package com.example.tillin.ui.screen.stats.week

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
class WeekTabViewModel @Inject constructor(
    private val statsRepository: StatsRepository
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
            val sundayMillis = sunday.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

            val dataPackage = statsRepository.getWeeklyDataForUI(sundayMillis)

            _state.value = _state.value.copy(til = dataPackage.tils, weeklyStats = dataPackage.stats, isLoading = false)

        } catch (e: Exception) {
            Log.d("loadWeekTil", "오류 발생 : ${e.message}")
        }
    }

    fun saveWeekStats(
        date: LocalDate,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, success = false)
            try {
                val timestamp = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

                statsRepository.updateWeeklyStats(timestamp)

                loadWeekStats(date)

                Log.e("TILLIN_DEBUG", "통계 저장 성공")
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