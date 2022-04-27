package com.adl.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.firebaseauthentication.adapter.ChatAdapter
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    var listDataChat = ArrayList<MessageModel>()
    lateinit var  chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().getReference("CHAT")
        chatAdapter = ChatAdapter(listDataChat)

        rcChat.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }

        btn_send_data.setOnClickListener({
            sendData()
        })

        database?.child("message")?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listDataChat.clear()
                for(data in snapshot.children){
                    listDataChat.add(MessageModel((data.key)!!.toLong(),data.value.toString()))
                    chatAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun sendData(){
        database?.child("message")?.child(System.currentTimeMillis().toString())?.setValue(et_message.text.toString())
    }
}