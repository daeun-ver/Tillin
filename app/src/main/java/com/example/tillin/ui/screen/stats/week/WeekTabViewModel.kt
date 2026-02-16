package com.example.tillin.ui.screen.stats.week

import androidx.lifecycle.ViewModel
import com.example.tillin.data.remote.OpenAIService
import com.example.tillin.data.repository.TilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeekTabViewModel @Inject constructor(
    private val repository: TilRepository,
    private val openAIService: OpenAIService
): ViewModel() {
    private val _state = MutableStateFlow(WeekTabState())

    fun getWeekRange(date: LocalDate): List<LocalDate> {
        val dayOfWeek = date.dayOfWeek.value % 7
        val sunday = date.minusDays(dayOfWeek.toLong())
        return (0..6).map { sunday.plusDays(it.toLong()) }
    }
}