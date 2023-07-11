package com.example.todoapp.view

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.model.Model
import com.example.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : androidx.fragment.app.Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val txtTitle =  view.findViewById<TextView>(R.id.txtTodo)

        txtTitle.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        btnAdd.setOnClickListener{
            val txtTitle =  view.findViewById<EditText>(R.id.txtTitleTodo)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId

            val checkedRadioButton = view.findViewById<RadioButton>(checkedRadioButtonId)
            val selectedTag = checkedRadioButton.tag

            viewModel.update(uuid,txtTitle.text.toString(),txtNotes.text.toString(), selectedTag.toString().toInt())

            Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner){
            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
            val txtTitle = view?.findViewById<EditText>(R.id.txtTitleTodo)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

        }
    }


}