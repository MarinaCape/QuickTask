package com.cape.quicktask.feature_task.presentation.calendar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cape.quicktask.core.domain.fold
import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.use_case.AddTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.DeleteTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTasksByDateUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getTasksByDateUseCase: GetTasksByDateUseCase
) : ViewModel() {

    private var _state = mutableStateOf(CalendarState())
    val state: State<CalendarState> = _state

    private var getTasksJob: Job? = null

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.GetEventsByDay-> getTasks(event.date)
        }
    }

    private fun getTasks(date: String) {
        getTasksJob?.cancel()
        getTasksJob = getTasksByDateUseCase(date)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                )
            }
            .launchIn(viewModelScope)
    }

}

sealed class UiEvent {
    class Error(val message: String) : UiEvent()
}