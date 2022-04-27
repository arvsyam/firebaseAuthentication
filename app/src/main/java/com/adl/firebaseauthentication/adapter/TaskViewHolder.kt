package com.adl.firebaseauthentication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adl.firebaseauthentication.Todo
import kotlinx.android.synthetic.main.item.view.*

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val go = view.lblActivity
    val wkt = view.lblTime
    val no = view.lblNo
    val close = view.btn_delete

    fun  bind(data: Todo.Todo, pos:Int, adapter: TaskAdapter){
        go.setText(data.doSome)
        wkt.setText(data.jam)
        no.setText((pos+1).toString()+".")

        close.setOnClickListener({


        })
    }
}