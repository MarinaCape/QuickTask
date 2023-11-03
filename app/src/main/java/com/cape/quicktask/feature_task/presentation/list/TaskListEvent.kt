package com.cape.quicktask.feature_task.presentation.list

import com.cape.quicktask.feature_task.domain.model.Task

sealed class TaskListEvent{
    class DeleteTask(val task: Task): TaskListEvent()
    object UndoDeleteTask : TaskListEvent()
}