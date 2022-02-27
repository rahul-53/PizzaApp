package com.rahul.pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.pizzaapp.adapters.CartAdapter
import com.rahul.pizzaapp.model.local.PizzaEntity
import com.rahul.pizzaapp.viewmodel.CartViewModel
import com.rahul.pizzaapp.viewmodel.CartViewModelFactory
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    private lateinit var viewModel: CartViewModel
    private val cartList = mutableListOf<PizzaEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartAdapter = CartAdapter(cartList)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = cartAdapter

        val appObj = application as CartApplication
        val repository = appObj.repository
        val viewModelFactory = CartViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)

        viewModel.getItems().observe(this, Observer {
            cartList.clear()
            cartList.addAll(it)
            cartAdapter.notifyDataSetChanged()
        })
        viewModel.totalPrice().observe(this, Observer {
            text_totalPrice.text= "Total Price is ₹ $it"
        })
    }
}