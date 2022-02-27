package com.rahul.pizzaapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rahul.pizzaapp.api.ApiService
import com.rahul.pizzaapp.model.remote.PizzaResponse

class PizzaRepository(private val pizzaApiService: ApiService) {
    private val pizzaLiveData= MutableLiveData<PizzaResponse>()

    val pizzas: LiveData<PizzaResponse>
        get() = pizzaLiveData


    suspend fun getPizza(){
        val result= pizzaApiService.getPizza()
        if (result?.body()!=null){
            pizzaLiveData.postValue(result.body())

        }
    }

}