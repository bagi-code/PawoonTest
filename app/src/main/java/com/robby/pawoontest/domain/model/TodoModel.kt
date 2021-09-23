package com.robby.pawoontest.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
data class TodoModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "completed") var completed: Boolean = false,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "userId") var userId: Int = 0
)