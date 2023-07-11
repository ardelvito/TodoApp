package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Model

//on check change
//listener binding
interface TodoCheckedChangeListener{
    fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Model.Todo)
}

interface TodoEditClickListener{
    fun onTodoEditClick(view: View)
}

interface RadioButtonListener{
    fun onRadioClick(view: View, priority: Int, obj: Model.Todo)
}

interface TodoSaveChangesListener{
    fun onSaveChangeClick(view:View, obj: Model.Todo)
}