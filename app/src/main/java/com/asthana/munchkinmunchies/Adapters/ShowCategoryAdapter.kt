package com.asthana.munchkinmunchies.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asthana.munchkinmunchies.R
import com.bumptech.glide.Glide

class ShowCategoryAdapter(private val context: Context, private val itemList: ArrayList<ItemCardModel>) :
    RecyclerView.Adapter<ShowCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.showdetails, parent, false)
        return ViewHolder(view)
    }
    fun setItems(items: ArrayList<ItemCardModel>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.imageView)
        holder.textViewDishName.text = item.dishTitle
        holder.ratingBar.rating = item.stars
        holder.author.text = item.author
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgshow)
        val textViewDishName: TextView = itemView.findViewById(R.id.textViewDishName)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating)
        val author: TextView = itemView.findViewById(R.id.author)
    }
    }
