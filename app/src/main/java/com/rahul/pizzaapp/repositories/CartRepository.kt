package com.rahul.pizzaapp.repositories

import androidx.lifecycle.LiveData
import com.rahul.pizzaapp.model.local.PizzaDao
import com.rahul.pizzaapp.model.local.PizzaEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartRepository(private val myDao: PizzaDao) {

    fun addItems(myEntity: PizzaEntity){
        CoroutineScope(Dispatchers.IO).launch {
            myDao.insertItems(myEntity)
        }

    }
    fun getItems(): LiveData<List<PizzaEntity>> {
        return myDao.getItems()
    }
    fun totalPrice(): LiveData<Double> {
        return myDao.totalPrice()
    }
}