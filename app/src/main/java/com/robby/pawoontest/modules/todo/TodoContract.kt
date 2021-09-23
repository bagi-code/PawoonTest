package com.robby.pawoontest.modules.todo

import com.robby.pawoontest.base.BasePresenter
import com.robby.pawoontest.base.BaseView
import com.robby.pawoontest.domain.model.TodoModel

interface TodoContract {

    interface View: BaseView {
        fun onTodoSuccess(todoModel: List<TodoModel>)
        fun onTodoOffline(todoModel: List<TodoModel>)
        fun onTodoFailed(message : String)
    }

    interface Presenter: TodoContract, BasePresenter {
        fun getTodoList()
        fun getTodoListOffline()
        fun insertTodo(todoModel: TodoModel)
        fun insertTodoAll(todoModel: List<TodoModel>)
        fun deleteTodo(todoModel: TodoModel)
        fun updateTodo(todoModel: TodoModel)
    }

}