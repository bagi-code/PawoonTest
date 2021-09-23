package com.robby.pawoontest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.robby.pawoontest.domain.model.TodoModel

data class TodoResponse(
    @Expose
    @SerializedName("completed")
    val completed: Boolean?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("userId")
    val userId: Int?
) {
    fun clean() = TodoModel(
        completed = completed?:false,
        id = id?:0,
        title = title.orEmpty(),
        userId = userId ?:0
    )
}