package com.rahul.pizzaapp.api

import com.rahul.pizzaapp.model.remote.PizzaResponse

sealed class NetworkHelper {
    data class OnSuccess(val responseList: List<PizzaResponse>) : NetworkHelper()

    data class OnFailure(val error: String) : NetworkHelper()

}