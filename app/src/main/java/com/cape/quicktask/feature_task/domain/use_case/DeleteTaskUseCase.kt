package com.cape.quicktask.feature_task.domain.use_case

import com.cape.quicktask.core.domain.use_case.UseCase
import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.repository.TaskRepository

class DeleteTaskUseCase(
    private val repository: TaskRepository
): UseCase<Unit, Task>() {

    override suspend fun run(params: Task) = repository.deleteTask(params)

}