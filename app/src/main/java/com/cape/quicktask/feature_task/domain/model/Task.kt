package com.cape.quicktask.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Task(
    val title: String,
    val content: String,
    val date: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)