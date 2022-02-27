package com.rahul.pizzaapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahul.pizzaapp.ItemListener
import com.rahul.pizzaapp.R
import com.rahul.pizzaapp.model.remote.Crust
import kotlinx.android.synthetic.main.pizza_item_layout.view.*
import java.lang.StringBuilder

class PizzaAdapter(var pizzaList: List<Crust>, val listener: ItemListener)
    :RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        return PizzaViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.pizza_item_layout, parent,false))
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val currentPizzaList = pizzaList[position]
        var size = StringBuilder()
        var crust = StringBuilder()
        var sizePrice = 0.0
        var cartPrice=0.0
        holder.itemView.apply {
            text_pizza_title.text = currentPizzaList.name
            // text_pizza_description.text=currentPizzaList.description
        }
        holder.itemView.apply {
            val sizeArray = arrayOf("Regular", "Medium", "Large")
            val crustArray = arrayOf("Hand-tossed", "Cheese Burst")


            sp_size.adapter =
                ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    sizeArray
                )
            sp_crust.adapter = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                crustArray
            )

            sp_size.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    @SuppressLint("SetTextI18n")
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when {
                            sizeArray[position] == "Regular" -> {
                                sizePrice = currentPizzaList.sizes?.get(position)?.price!!

                                text_price.text =
                                    "₹ $sizePrice"
                                cartPrice=sizePrice
                                size.clear()
                                size.append(sizeArray[position])


                            }
                            sizeArray[position] == "Medium" -> {
                                sizePrice = currentPizzaList.sizes?.get(position)?.price!!
                                text_price.text =
                                    "₹ $sizePrice"
                                cartPrice=sizePrice
                                size.clear()
                                size.append(sizeArray[position])
                            }
                            sizeArray[position] == "Large" -> {
                                sizePrice = currentPizzaList.sizes?.get(position)?.price!!

                                text_price.text =
                                    "₹ $sizePrice"
                                cartPrice=sizePrice
                                size.clear()
                                size.append(sizeArray[position])


                            }
                        }


                    }

                    @SuppressLint("SetTextI18n")
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        text_price.text = "₹ 199"
                    }

                }
            sp_crust.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    @SuppressLint("SetTextI18n")
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when {
                            crustArray[position] == "Hand-tossed" -> {

                                val crustPrice = 100.00
                                val finalPrice = crustPrice + sizePrice
                                text_price.text =
                                    "₹ $finalPrice"
                                cartPrice=finalPrice
                                crust.clear()
                                crust.append(crustArray[position])
                            }
                            crustArray[position] == "Cheese Burst" -> {
                                val crustPrice = 120.00
                                val finalPrice = crustPrice + sizePrice
                                text_price.text =
                                    "₹ $finalPrice"
                                cartPrice=finalPrice
                                crust.clear()
                                crust.append(crustArray[position])
                            }

                        }


                    }

                    @SuppressLint("SetTextI18n")
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        text_price.text = "₹ 199"
                    }

                }
        }


        holder.itemView.button_add_to_cart.setOnClickListener {
            listener.addCartItems(
                currentPizzaList.name!!,
                size.toString(), crust.toString(), cartPrice
            )
        }
    }

    override fun getItemCount(): Int {
        return pizzaList.size
    }

    inner class PizzaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}


}