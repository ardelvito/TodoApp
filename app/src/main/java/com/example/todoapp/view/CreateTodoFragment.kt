package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.model.Model
import com.example.todoapp.viewmodel.DetailTodoViewModel


class CreateTodoFragment : Fragment() {

    private lateinit var viewModel:DetailTodoViewModel
//    private init var viewModel:DetailTodoViewModel = ?null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

//        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
//        btnAdd.setOnClickListener {
//            val txtTitle =  view.findViewById<EditText>(R.id.txtTitleTodo)
//            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
//            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
//
//            val checkedRadioButton = view.findViewById<RadioButton>(checkedRadioButtonId)
//            val selectedTag = checkedRadioButton.tag
//
//            val todo = Model.Todo(txtTitle.text.toString(), txtNotes.text.toString(), selectedTag.toString().toInt(), 0)
//
//            viewModel.addTodo(todo)
//
//            Toast.makeText(view.context, "TodoCreated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

    }

}