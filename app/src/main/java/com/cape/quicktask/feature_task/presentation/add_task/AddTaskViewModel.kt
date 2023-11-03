package com.cape.quicktask.feature_task.presentation.add_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cape.quicktask.core.domain.fold
import com.cape.quicktask.core.domain.use_case.UseCase
import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.use_case.AddTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskTitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Write a title..."
        )
    )
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskContent = mutableStateOf(
        TaskTextFieldState(
            hint = "Add some content"
        )
    )
    val taskContent: State<TaskTextFieldState> = _taskContent

    private var taskDate: String = ""

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    getTaskUseCase(taskId)?.also { task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        _taskContent.value = taskContent.value.copy(
                            text = task.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.AddTask -> addTask()
            is AddTaskEvent.TitleChangeValue -> {
                _taskTitle.value = _taskTitle.value.copy(
                    text = event.title,
                    isHintVisible = event.title.isEmpty()
                )
            }

            is AddTaskEvent.ContentChangeValue -> {
                _taskContent.value = _taskContent.value.copy(
                    text = event.content,
                    isHintVisible = event.content.isEmpty()
                )
            }
            is AddTaskEvent.DateChangeValue -> {
                taskDate = event.date
            }
        }
    }

    private fun addTask() {
        if (taskTitle.value.text.isNotEmpty() && taskContent.value.text.isNotEmpty()) {
            viewModelScope.launch {
                addTaskUseCase(
                    Task(
                        taskTitle.value.text,
                        taskContent.value.text,
                        taskDate,
                        System.currentTimeMillis(),
                        currentTaskId
                    )
                ).fold(
                    onSuccess = {
                        _uiEventFlow.emit(UiEvent.TaskSaved)
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
    object TaskSaved : UiEvent()
}
