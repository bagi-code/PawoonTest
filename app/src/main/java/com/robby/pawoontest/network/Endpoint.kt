package com.robby.pawoontest.network

import com.robby.pawoontest.data.model.TodoResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Endpoint {
    @GET("todos")
    fun todoList() : Observable<ArrayList<TodoResponse>>
}