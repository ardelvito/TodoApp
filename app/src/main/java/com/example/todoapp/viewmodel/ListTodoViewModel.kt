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
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

        val todoLD = MutableLiveData<List<Model.Todo>>()
        val todoLoadErrorLD = MutableLiveData<Boolean>()
        val loadingLD = MutableLiveData<Boolean>()
        private var job = Job()

        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO


        fun refresh() {
            loadingLD.value = true
            todoLoadErrorLD.value = false
            launch {
//                val db = Room.databaseBuilder(
//                    getApplication(),
//                    TodoDatabase::class.java, "newtododb").build()
                               val db = buildDB(getApplication())

                todoLD.postValue(db.todoDao().selectAllTodo())
            }
        }

        fun clearTask(todo: Model.Todo) {
            launch {
                val db = Room.databaseBuilder(
                    getApplication(),
                    TodoDatabase::class.java, "newtododb").build()
//                db.todoDao().deleteTodo(todo)
                db.todoDao().updateIsDone(todo.uuid, 1)

                todoLD.postValue(db.todoDao().selectAllTodo())
            }
        }



    }


