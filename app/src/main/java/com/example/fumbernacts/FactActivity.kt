package com.example.fumbernacts

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


class FactActivity : AppCompatActivity() {

    private val adapter = FactAdapter()
    private var tracker: SelectionTracker<Long>? = null
    private var nbapi = NumbersApi()
    private var isMath : Boolean = false
    private var selectedFact : Int = 0
    private lateinit var factTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fact)
        val recyclerView = findViewById<RecyclerView>(R.id.fact_list)
        factTextView = findViewById(R.id.factText)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.list = List(10000) { it }
        adapter.notifyDataSetChanged()

        tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            recyclerView,
            StableIdKeyProvider(recyclerView),
            FactItemDetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()

        adapter.tracker = tracker

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    try {
                        selectedFact = tracker?.selection!!.first().toInt()
                        startFactCoroutine()
                    } catch (e : Exception) {
                        // Selection was empty
                    }
                }
            })
    }

    fun startFactCoroutine() = GlobalScope.launch {
        var fact:Fact
        if(isMath) {
            fact = nbapi.getMathFact(selectedFact)
        } else {
            fact = nbapi.getTrivia(selectedFact)
        }
        factTextView.text = fact.fact
    }

}