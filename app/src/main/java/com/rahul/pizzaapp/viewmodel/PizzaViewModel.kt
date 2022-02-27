package com.rahul.pizzaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rahul.pizzaapp.model.remote.PizzaResponse
import com.rahul.pizzaapp.repositories.PizzaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PizzaViewModel(private val repository: PizzaRepository) :ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getPizza()
        }
    }

    val pizzas: LiveData<PizzaResponse>
        get() = repository.pizzas

}


class PizzaViewModelFactory(private val repository: PizzaRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PizzaViewModel(repository) as T
    }
}