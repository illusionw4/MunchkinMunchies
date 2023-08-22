package com.asthana.munchkinmunchies.Fragments

import Ingredient
import IngredientAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.asthana.munchkinmunchies.R


class AllDetails : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming you have a reference to the ListView in your layout file with the ID "listView"
        val recyclerView = view.findViewById<RecyclerView>(R.id.listviewww)

// Create a list of ingredients
        val ingredients = listOf(
            Ingredient("Ingredient 1"),
            Ingredient("Ingredient 2"),
            Ingredient("Ingredient 3")
            // Add more ingredients as needed
        )

// Create the adapter and set it to the RecyclerView
        val adapter = IngredientAdapter(requireContext(), R.layout.allinfocard, ingredients)
        recyclerView.adapter = adapter
    }


}