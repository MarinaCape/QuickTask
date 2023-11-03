package com.cape.quicktask.feature_task.domain.use_case

import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repository.getTasks()
}