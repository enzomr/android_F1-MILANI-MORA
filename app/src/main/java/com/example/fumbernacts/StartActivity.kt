package com.example.fumbernacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import java.util.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //val datePicker = findViewById<DatePicker>(R.id.datePicker)
        //datePicker.minDate = 0
    }

    fun onMathButtonClick(view: View) {
        val intent = Intent(this, FactActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun onTriviaButtonClick(view: View) {
        val intent = Intent(this, FactActivity::class.java).apply {
        }
        startActivity(intent)
    }
}