package com.adl.firebaseauthentication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.chat_holder.view.*

class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val pesan = view.txtPesan
    val time = view.txtTime


    fun bindData(adapter: ChatAdapter, position: Int) {


        time.setText(adapter.data.get(position)?.waktu.toString())
        pesan.setText(adapter.data.get(position)?.pesan.toString())


    }
}