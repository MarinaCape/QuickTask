package com.cape.quicktask.feature_task.data.data_source

import androidx.room.*
import com.cape.quicktask.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.Date

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE date = :date")
    fun getTasksByDate(date: String): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}