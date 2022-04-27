package com.adl.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.ktx.Firebase

class FirestoreCRUD : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore_crud)

        db = FirebaseFirestore.getInstance()
//        writeData()
//        readData()
        searchData()
    }

    fun readData(){
        db.collection("manusia").get().addOnSuccessListener {
                result ->
            for(data in result){
                Log.d("Data Firestore : ","${data.id}")

                for (details in data.data){
                    Log.d("detail", details.value.toString())
                }

            }
        }
    }

    fun searchData(){
        db.collection("manusia").document("goblay").collection("makanan").whereEqualTo("sayur","sop").addSnapshotListener { value, error ->
            if(value != null){
                for(data in value){
                   Log.d("search","${data.id}")
                }
            }
        }

    }

}