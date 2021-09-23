package com.robby.pawoontest.domain

import androidx.room.*
import com.robby.pawoontest.domain.model.TodoModel

@Dao
interface TodoDao {

    @Query("Select * from todolist")
    fun getTodoAll() : List<TodoModel>
    @Insert
    fun insertTodoAll(todoModel: List<TodoModel>)
    @Insert
    fun insertTodo(todoModel: TodoModel)
    @Update
    fun updateTodo(todoModel: TodoModel)
    @Delete
    fun deleteTodo(todoModel: TodoModel)
}