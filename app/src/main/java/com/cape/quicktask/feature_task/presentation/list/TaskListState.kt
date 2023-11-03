package com.cape.quicktask.feature_task.presentation.list

import com.cape.quicktask.feature_task.domain.model.Task

data class TaskListState(
    val tasks: List<Task> = emptyList()
)