package com.rahul.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.pizzaapp.adapters.PizzaAdapter
import com.rahul.pizzaapp.api.ApiService
import com.rahul.pizzaapp.api.Network
import com.rahul.pizzaapp.model.local.PizzaEntity
import com.rahul.pizzaapp.model.remote.Crust
import com.rahul.pizzaapp.repositories.PizzaRepository
import com.rahul.pizzaapp.viewmodel.CartViewModel
import com.rahul.pizzaapp.viewmodel.CartViewModelFactory
import com.rahul.pizzaapp.viewmodel.PizzaViewModel
import com.rahul.pizzaapp.viewmodel.PizzaViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ItemListener {

    lateinit var pizzaViewModel: PizzaViewModel
    lateinit var pizzaAdapter: PizzaAdapter
    private val pizzaResponse = mutableListOf<Crust>()
    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pizzaAdapter = PizzaAdapter(pizzaResponse,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = pizzaAdapter

        val pizzaApiService = Network.getInstance().create(ApiService::class.java)
        val repository = PizzaRepository(pizzaApiService)

        pizzaViewModel = ViewModelProvider(
            this,
            PizzaViewModelFactory(repository)
        ).get(PizzaViewModel::class.java)
        pizzaViewModel.pizzas.observe(this, Observer {
            pizzaAdapter.pizzaList = it.crusts!!

            pizzaAdapter.notifyDataSetChanged()
        })

        val appObj = application as CartApplication
        val cartRepository = appObj.repository
        val viewModelFactory = CartViewModelFactory(cartRepository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)

        button_cart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

    }

    override fun addCartItems(name: String, size: String, crust: String, price: Double) {
        val myEntity = PizzaEntity(name, size, crust, price)
        viewModel.addItems(myEntity)
    }
}