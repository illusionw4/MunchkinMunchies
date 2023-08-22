package com.asthana.munchkinmunchies.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.asthana.munchkinmunchies.R
import com.bumptech.glide.Glide

class Dishdetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dishdetail, container, false)

        // Retrieve the recipe details passed from the ShowCategory fragment
        val title = arguments?.getString("title")
        val imageUrl = arguments?.getString("imageUrl")
        val calories = arguments?.getString("calories")
        val ingredientLines = arguments?.getStringArrayList("ingredientLines")

        // Update the UI to display the recipe details
        val recipeTitleTextView: TextView = view.findViewById(R.id.textViewRecipeTitle)
        val recipeImageView: ImageView = view.findViewById(R.id.imageViewRecipe)
        val caloriesTextView: TextView = view.findViewById(R.id.textViewCalories)
        val ingredientsTitleTextView: TextView = view.findViewById(R.id.textViewIngredientsTitle)
        val ingredientsTextView: TextView = view.findViewById(R.id.textViewIngredients)

        recipeTitleTextView.text = title
        caloriesTextView.text = "$calories"

        // Load the recipe image using an image loading library like Glide or Picasso
        Glide.with(requireContext())
            .load(imageUrl)
            .error(R.drawable.banner) // Optional error image
            .into(recipeImageView)


        // Display the list of ingredients
        if (!ingredientLines.isNullOrEmpty()) {
            ingredientsTitleTextView.visibility = View.VISIBLE
            ingredientsTextView.visibility = View.VISIBLE
            val ingredientsString = ingredientLines.joinToString("\n")
            ingredientsTextView.text = ingredientsString
        } else {
            ingredientsTitleTextView.visibility = View.GONE
            ingredientsTextView.visibility = View.GONE
        }

        return view
    }
}
