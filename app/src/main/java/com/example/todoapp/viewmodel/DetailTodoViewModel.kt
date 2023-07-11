package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp.model.Model
import com.example.todoapp.model.TodoDatabase
import com.example.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {

    private val job = Job();
    val todoLD = MutableLiveData<Model.Todo>()

    fun addTodo(todo: Model.Todo){
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                TodoDatabase::class.java, "newtododb").build()
            val db = buildDB(getApplication())

            db.todoDao().insertAll(todo)

        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(uuid: Int){
        launch {
            val db = buildDB(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
        }
    }

    fun update(id: Int, title:String, notes:String, priority:Int){
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(id, title, notes, priority)
        }
    }

}