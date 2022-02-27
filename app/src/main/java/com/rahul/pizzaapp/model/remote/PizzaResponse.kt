package com.rahul.pizzaapp.model.remote

data class PizzaResponse(
    val crusts: List<Crust>,
    val defaultCrust: Int,
    val description: String,
    val isVeg: Boolean,
    val name: String
)