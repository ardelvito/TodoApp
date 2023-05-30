package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Model

class TodoListAdapter(val todos: ArrayList<Model.Todo>, val adapter: (Model.Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //render checkbox
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkBoxTask)
        checkTask.text = todos[position].title
        checkTask.isChecked = false

        checkTask.setOnCheckedChangeListener{ compoundButton, b->
            adapter(todos[position])
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun updateTodoList(newTodos: ArrayList<Model.Todo>){
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }
}