package com.rahul.pizzaapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PizzaDao {
    /**
     * This function will insert all the items in the Database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(myEntity: PizzaEntity)

    /**
     * This function will return all the items from the Database
     */
    @Query("select * from my_cart_table")
    fun getItems(): LiveData<List<PizzaEntity>>

    /**
     * This function will return  the total number of row from database table
     */
    @Query("SELECT COUNT(*) FROM my_cart_table")
    fun count(): Int
    @Query("SELECT SUM(price) from my_cart_table")
    fun totalPrice(): LiveData<Double>
}