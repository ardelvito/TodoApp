package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Model
import com.example.todoapp.viewmodel.ListTodoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListFragment : Fragment() {

    //view model untuk ambil data dari sqllite;
    private  lateinit var viewModel: ListTodoViewModel
    private val adapter = TodoListAdapter(arrayListOf(), { item -> viewModel.clearTask(item)})


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)

        viewModel.refresh()
        val recViewTodo = view.findViewById<RecyclerView>(R.id.recViewTodo)
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = adapter

        val fabAddTodo = view.findViewById<FloatingActionButton>(R.id.fabAddToDo)
        fabAddTodo.setOnClickListener{
            val action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            adapter.updateTodoList(it as ArrayList<Model.Todo> /* = java.util.ArrayList<com.example.todoapp.model.Model.Todo> */)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtEmpty)

            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility =View.GONE
            }
        })
    }

}