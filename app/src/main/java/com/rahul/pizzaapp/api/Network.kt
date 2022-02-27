package com.rahul.pizzaapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    //https://android.free.beeceptor.com/pizzas

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android.free.beeceptor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}