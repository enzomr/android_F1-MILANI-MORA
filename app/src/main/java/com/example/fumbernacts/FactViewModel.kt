package com.example.fumbernacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class FactViewModel : ViewModel() {
    private val numbersApi = NumbersApi()

    val factLiveData: LiveData<Fact> = liveData{
        val fact: Fact = numbersApi.getFact(1)
        emit(fact)
    }
}