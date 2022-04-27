package com.adl.firebaseauthentication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.firebaseauthentication.R
import com.adl.firebaseauthentication.Todo

class TaskAdapter(val todoList:ArrayList<Todo.Todo>): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(todoList.get(position),position, this@TaskAdapter)
        Log.d("onholder","${todoList}")
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}