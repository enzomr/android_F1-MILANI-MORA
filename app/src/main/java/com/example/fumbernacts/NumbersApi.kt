package com.example.fumbernacts

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.URL

class NumbersApi {

    private val httpClient: HttpClient = HttpClient(CIO)

    suspend fun getTrivia(number: Int): Fact {
        val fact: String = httpClient.get("http://numbersapi.com/$number")
        return Fact(fact)
    }

    suspend fun getMathFact(number: Int): Fact {
        val fact: String = httpClient.get("http://numbersapi.com/$number/math")
        return Fact(fact)
    }

    suspend fun getDateFact(day: Int, month: Int): Fact {
        val fact: String = httpClient.get("http://numbersapi.com/$month/$day/date")
        return Fact(fact)
    }

    suspend fun getRandomFact(): Fact {
        val fact: String = httpClient.get("http://numbersapi.com/random")
        return Fact(fact)
    }
}