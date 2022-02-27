package com.rahul.pizzaapp

import android.app.Application
import com.rahul.pizzaapp.model.local.PizzaDatabase
import com.rahul.pizzaapp.repositories.CartRepository

class CartApplication:Application() {
    private val myDao by lazy {
        val roomDatabase = PizzaDatabase.getDatabase(this)
        roomDatabase.getMyDao()
    }
    val repository: CartRepository by lazy {
        CartRepository(myDao)
    }
}