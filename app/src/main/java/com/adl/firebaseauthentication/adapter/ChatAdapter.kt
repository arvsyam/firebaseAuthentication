package com.adl.firebaseauthentication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.firebaseauthentication.MessageModel
import com.adl.firebaseauthentication.R

class ChatAdapter(val data: ArrayList<MessageModel>): RecyclerView.Adapter<ChatViewHolder>() {
    lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        this.parent = parent

        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_holder,parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bindData(this@ChatAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}