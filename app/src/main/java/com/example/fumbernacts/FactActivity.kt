package com.example.fumbernacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fact)

        val lv = findViewById<RecyclerView>(R.id.fact_list)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        val adapter = FactAdapter(IntArray(100))
        lv.layoutManager = llm
        lv.adapter =adapter
    }
}