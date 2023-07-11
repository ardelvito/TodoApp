package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Model

    class TodoListAdapter(val todos: ArrayList<Model.Todo>, val adapter: (Model.Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
        TodoCheckedChangeListener,
        TodoEditClickListener{
//    class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view)
    class TodoViewHolder(val view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

            //instatiate binding object
            val view = TodoItemLayoutBinding.inflate(inflater, parent, false)

            return TodoViewHolder(view)
        }

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            val todo = todos[position]

            //data binding instantiate
            holder.view.todo = todos[position]
            holder.view.checkboxListener = this
            holder.view.editListener = this

//        //render checkbox
//        val priority = when(todos[position].priority){
//            1 -> "Low"
//            2 -> "Medium"
//            else-> "High"
//        }
//        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkBoxTask)
//        checkTask.text =  "$[$priority] ${todos[position].title}"
//        checkTask.isChecked = false
//
//        checkTask.setOnCheckedChangeListener{ compoundButton, b->
//            adapter(todos[position])
//        }
//
//        //button edit
//        var btnEditTask = holder.view.findViewById<ImageButton>(R.id.btnEditTask)
//        btnEditTask.setOnClickListener{
//            val action = TodoListFragmentDirections.actionEditTodo(todos[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }


    }

        override fun getItemCount(): Int {
            return todos.size
        }

        fun updateTodoList(newTodos: ArrayList<Model.Todo>){
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }

        override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Model.Todo) {
            if(isChecked){
                adapter(obj)
            }
        }

        override fun onTodoEditClick(view: View) {
            val uuid = view.tag.toString().toInt()
            val action = TodoListFragmentDirections.actionEditTodo(uuid)
            Navigation.findNavController(view).navigate(action)
        }
    }