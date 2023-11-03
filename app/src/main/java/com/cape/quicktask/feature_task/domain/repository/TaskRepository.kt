package com.cape.quicktask.feature_task.domain.repository

import com.cape.quicktask.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.Date

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun getTasksByDate(date: String): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}