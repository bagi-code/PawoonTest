package com.robby.pawoontest.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robby.pawoontest.domain.model.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TodoDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TodoDatabase::class.java,
                    "tododatabase.db"
                )
                    .build()
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}