package com.cape.quicktask.feature_task.presentation.add_task

data class TaskTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
