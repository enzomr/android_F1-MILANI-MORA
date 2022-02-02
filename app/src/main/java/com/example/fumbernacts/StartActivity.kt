package com.example.fumbernacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {

    private lateinit var explanationTextView: TextView
    private lateinit var randomFactTextView: TextView
    private val model: FactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        explanationTextView = findViewById<TextView>(R.id.explanationText)
        randomFactTextView = findViewById<TextView>(R.id.randomFactText)

        model.dateFactLiveData.observe(this, Observer<Fact>{ fact ->
            explanationTextView.text = fact.fact
        })

        model.randomFactLiveData.observe(this, Observer<Fact>{ fact ->
            randomFactTextView.text = fact.fact
        })

        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        datePicker.setOnDateChangedListener(OnDateChangedListener{ view, year, month, day ->
            onDateChanged(view, year, month+1, day)
        })

        startRandomCoroutine()
    }

    private fun onDateChanged(view: View, year: Int, month: Int, day: Int){
        model.loadDateFact(day, month)
    }

    fun startRandomCoroutine() = lifecycleScope.launch {
        Log.d("COROUTINE", "started")
        while (true){
            Log.d("COROUTINE", "sent")
            model.loadRandomFact()
            delay(5000)
        }
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
