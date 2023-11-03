package com.cape.quicktask.feature_task.domain.use_case

import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.repository.TaskRepository

class GetTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(params: Int): Task? = repository.getTaskById(params)
}