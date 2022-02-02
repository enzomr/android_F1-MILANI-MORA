package com.example.fumbernacts

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.net.URL

class FactViewModel : ViewModel() {
    private val numbersApi = NumbersApi()

    val dateFactLiveData = MutableLiveData<Fact>()
    val randomFactLiveData = MutableLiveData<Fact>()

    fun loadRandomFact() = viewModelScope.launch {
        randomFactLiveData.value = numbersApi.getRandomFact()
    }

    fun loadDateFact(day: Int, month: Int) = viewModelScope.launch {
        dateFactLiveData.value = numbersApi.getDateFact(day, month)
    }

}