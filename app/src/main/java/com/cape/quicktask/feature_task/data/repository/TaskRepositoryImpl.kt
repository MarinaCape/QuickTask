package com.cape.quicktask.feature_task.data.repository

import com.cape.quicktask.feature_task.data.data_source.TaskDao
import com.cape.quicktask.feature_task.domain.model.Task
import com.cape.quicktask.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.Date

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override fun getTasksByDate(date: String): Flow<List<Task>> {
        return dao.getTasksByDate(date)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }
}