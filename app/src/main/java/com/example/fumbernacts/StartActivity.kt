package com.example.fumbernacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
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