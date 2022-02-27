package com.rahul.pizzaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.pizzaapp.R
import com.rahul.pizzaapp.model.local.PizzaEntity
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartAdapter(var cartList: List<PizzaEntity>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout,parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentList =cartList[position]
        holder.itemView.apply {
            pizza_name.text=currentList.title
            pizza_size.text=currentList.size
            pizza_crust.text=currentList.crust
            pizza_price.text="₹ "+currentList.price.toString()
        }
    }

    override fun getItemCount(): Int {
       return cartList.size
    }

}