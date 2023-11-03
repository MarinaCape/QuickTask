package com.cape.quicktask.feature_task.presentation.calendar

import com.cape.quicktask.feature_task.domain.model.Task

data class CalendarState(
    val tasks: List<Task> = emptyList()
)