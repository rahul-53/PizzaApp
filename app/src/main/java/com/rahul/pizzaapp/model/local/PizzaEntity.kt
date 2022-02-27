package com.rahul.pizzaapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_cart_table")
data class PizzaEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "crust") val crust: String,
    @ColumnInfo(name = "price") val price: Double
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}