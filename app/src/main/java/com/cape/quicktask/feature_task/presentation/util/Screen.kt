package com.cape.quicktask.feature_task.presentation.util

sealed class Screen(val route: String) {
    object TaskScreen: Screen("task_screen")
    object CalendarScreen: Screen("calendar_screen")
    object AddTaskScreen: Screen("add_task_screen")
}
