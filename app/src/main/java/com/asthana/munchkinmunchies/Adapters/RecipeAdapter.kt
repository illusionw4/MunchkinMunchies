package com.asthana.munchkinmunchies.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asthana.munchkinmunchies.R
import com.bumptech.glide.Glide

data class Recipe(val label: String, val image: String, val foodCategory: MutableList<String>, val calories: String, val ingredientLines: ArrayList<String>)


class RecipeAdapter(private var recipes: List<Recipe>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewDishName)
        val foodcate: TextView = itemView.findViewById(R.id.foodcat)
        val calories: TextView = itemView.findViewById(R.id.calorie)
        val imageView: ImageView = itemView.findViewById(R.id.imgshow)
    }

    interface OnItemClickListener {
        fun onItemClick(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.showdetails, parent, false)
        return RecipeViewHolder(itemView)
    }

    fun setData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.titleTextView.text = recipe.label
        val foodCategoriesFormatted = recipe.foodCategory.joinToString(", ")
        holder.foodcate.text = foodCategoriesFormatted
        holder.calories.text = recipe.calories
        // Use an image loading library like Picasso or Glide to load the image into the ImageView
        Glide.with(holder.itemView)
            .load(recipe.image)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
