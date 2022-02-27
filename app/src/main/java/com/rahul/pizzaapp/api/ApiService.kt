package com.rahul.pizzaapp.api

import com.rahul.pizzaapp.model.remote.PizzaResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("pizzas")
    suspend fun getPizza(): Response<PizzaResponse>
}