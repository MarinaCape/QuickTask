package com.cape.quicktask.feature_task.presentation.add_task

sealed class AddTaskEvent{
    object AddTask: AddTaskEvent()
    class TitleChangeValue(val title: String): AddTaskEvent()
    class ContentChangeValue(val content: String): AddTaskEvent()
    class DateChangeValue(val date: String): AddTaskEvent()
}