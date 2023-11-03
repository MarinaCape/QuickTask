package com.cape.quicktask.feature_task.di

import android.app.Application
import androidx.room.Room
import com.cape.quicktask.feature_task.data.data_source.TaskDao
import com.cape.quicktask.feature_task.data.data_source.TaskDatabase
import com.cape.quicktask.feature_task.data.repository.TaskRepositoryImpl
import com.cape.quicktask.feature_task.domain.repository.TaskRepository
import com.cape.quicktask.feature_task.domain.use_case.AddTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.DeleteTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTaskUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTasksByDateUseCase
import com.cape.quicktask.feature_task.domain.use_case.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(dataBase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(dataBase.taskDao)
    }

    @Provides
    @Singleton
    fun provideGetTasksUseCase(repository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddTaskUseCase(repository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskUseCase(repository: TaskRepository): GetTaskUseCase {
        return GetTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTasksByDateUseCase(repository: TaskRepository): GetTasksByDateUseCase {
        return GetTasksByDateUseCase(repository)
    }
}