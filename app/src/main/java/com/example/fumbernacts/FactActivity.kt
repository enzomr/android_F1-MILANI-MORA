package com.example.fumbernacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FactActivity : AppCompatActivity() {

    private val adapter = FactAdapter()
    private var tracker: SelectionTracker<Long>? = null
    private var isMath : Boolean = false
    private var selectedFact : Int = 0
    private lateinit var factTextView : TextView
    private val model: FactViewModel by viewModels()

    //Save selection
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SELECTION", selectedFact.toLong())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fact)
        val recyclerView = findViewById<RecyclerView>(R.id.fact_list)
        factTextView = findViewById(R.id.factText)

        model.factLiveData.observe(this, Observer<Fact>{ fact ->
            factTextView.text = fact.fact
        })

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

        if(savedInstanceState != null){
            tracker!!.select(savedInstanceState.getLong("SELECTION"))
        }

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    try {
                        selectedFact = tracker?.selection!!.first().toInt()
                        if(isMath) {
                            model.loadMathFact(selectedFact)
                        } else {
                            model.loadTrivia(selectedFact)
                        }
                    } catch (e : Exception) {
                        // Selection was empty
                    }
                }
            })
    }

}