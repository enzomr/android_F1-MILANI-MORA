package com.example.fumbernacts

class NumbersApi {

    fun getFact(i: Int): Fact {
       return Fact(i, "fact")
    }
}