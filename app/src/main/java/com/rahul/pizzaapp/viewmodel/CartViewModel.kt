package com.rahul.pizzaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahul.pizzaapp.model.local.PizzaEntity
import com.rahul.pizzaapp.repositories.CartRepository

class CartViewModel(private val repository: CartRepository):ViewModel() {
    fun addItems(taskEntity: PizzaEntity) {
        repository.addItems(taskEntity)
    }

    fun getItems(): LiveData<List<PizzaEntity>> {
        return repository.getItems()
    }
    fun totalPrice(): LiveData<Double> {
        return repository.totalPrice()
    }
}
class CartViewModelFactory(private val repository: CartRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(repository) as T
    }
}