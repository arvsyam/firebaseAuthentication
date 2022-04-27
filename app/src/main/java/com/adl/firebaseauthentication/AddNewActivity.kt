package com.adl.firebaseauthentication

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_new.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        btnAddTo.setOnClickListener({
            var data = Intent()
            data.putExtra("kegiatan",txtAct.text.toString())
            data.putExtra("waktu",txtTime.text.toString())
            setResult(RESULT_OK,data)
            finish()
        })

        btnTimeSelect.setOnClickListener({
            selectTime()
        })
    }

    fun selectTime(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            txtTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
        }
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }
}