package com.example.todoapp.model

import androidx.room.*
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Model.Todo)

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    fun  selectAllTodo(): List<Model.Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Model.Todo

    @Query("UPDATE todo SET title =:title, notes=:notes, priority=:priority WHERE uuid= :id")
    fun update(id:Int, title:String, notes:String, priority: Int)

    @Update
    suspend fun updateTodo(todo: Model.Todo)

    @Delete
    fun deleteTodo(todo: Model.Todo)

    @Query("UPDATE todo SET is_done =:is_done WHERE uuid= :id")
    fun updateIsDone(id:Int, is_done:Int)


}

