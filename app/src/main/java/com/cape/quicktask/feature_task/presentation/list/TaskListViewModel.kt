package com.cape.quicktask.feature_task.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cape.quicktask.core.domain.fold
import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.use_case.AddTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.DeleteTaskUseCase
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
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var getNotesJob: Job? = null

    private var lastTaskDeleted: Task? = null

    init {
        getTasks()
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.DeleteTask -> deleteTask(event.task)
            TaskListEvent.UndoDeleteTask -> addNote()
        }
    }

    private fun getTasks() {
        getNotesJob?.cancel()
        getNotesJob = getTasksUseCase()
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                )
            }
            .launchIn(viewModelScope)
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task).fold(
                onSuccess = {
                    lastTaskDeleted = task
                },
                onFailure = {
                    _uiEventFlow.emit(
                        UiEvent.Error(
                            it.message ?: "Error saving the task, try again."
                        )
                    )
                }
            )
        }
    }

    private fun addNote() {
        lastTaskDeleted?.let { task ->
            viewModelScope.launch {
                addTaskUseCase(
                    task
                ).fold(
                    onSuccess = {
                        lastTaskDeleted = null
                    },
                    onFailure = {
                        _uiEventFlow.emit(
                            UiEvent.Error(
                                it.message ?: "Error saving the task, try again."
                            )
                        )
                    })
            }
        }
    }
}

sealed class UiEvent {
    class Error(val message: String) : UiEvent()
}