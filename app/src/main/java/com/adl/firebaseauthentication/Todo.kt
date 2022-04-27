package com.adl.firebaseauthentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.firebaseauthentication.adapter.TaskAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.todo.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Todo : AppCompatActivity() {
    val listTodo = arrayListOf<Todo>()
    lateinit var todoAdapter: TaskAdapter

    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo)
        db = FirebaseFirestore.getInstance()


        readData()
        setDate()
        initcomponent()

    }

    private fun initcomponent(){
        btnAdd.setOnClickListener({
            intent = Intent(this,AddNewActivity::class.java)
            getResult.launch(intent)
        })

    }

    val getResult= registerForActivityResult( ActivityResultContracts.StartActivityForResult() ){
        if(it.resultCode == Activity.RESULT_OK) {
            val getData = it.data!!.getStringExtra("kegiatan")
            val getTime = it.data!!.getStringExtra("waktu")

            if((getData.toString() != "") && (getTime.toString() != "")){
                val newTask = hashMapOf(
                    "activity" to "${getData}",
                    "waktu" to "${getTime}"
                )

                val date = setDate()


                db.collection("tasks").add(newTask).addOnSuccessListener {
                        document ->
                    Log.d("add","${document}")
                }.addOnFailureListener { e ->
                    Log.d("Error", e.toString())
                }

                db.collection("Tasks").document(date).set(newTask).addOnSuccessListener {
                        document ->
                    Log.d("add","${document}")
                }.addOnFailureListener { e ->
                    Log.d("Error", e.toString())
                }




                Toast.makeText(this,"Data berhasil ditambahkan", Toast.LENGTH_LONG).show()
//
            }else{
                Toast.makeText(this,"Data tidak berhasil ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun readData(){

        db.collection("tasks")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("READ", "${document.get("waktu")} => ${document.data}")
                    listTodo.add(Todo(document.get("activity").toString(),document.get("waktu").toString()))
                    Log.d("panjangread","${listTodo.size}")


                }
                Log.d("panjangluar","${listTodo.size}")
                showList(listTodo)


            }
            .addOnFailureListener { exception ->
                Log.w("READERROR", "Error getting documents.", exception)
            }

    }

    fun showList(list:ArrayList<Todo>){
        todoAdapter = TaskAdapter(list)
        Log.d("panjang","${list.size}")
        rcTodo.apply{
            layoutManager = LinearLayoutManager(this@Todo)
            adapter = todoAdapter
        }
    }


    fun setDate():String{
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        val formatedDate = sdf.format(date)
        Log.d("date","${formatedDate}")

        return formatedDate
    }

    fun saveToFirestore(){

    }



    override fun onRestart() {
        super.onRestart()
        listTodo.clear()
        readData()
    }
    data class Todo(val doSome:String, val jam:String)
}