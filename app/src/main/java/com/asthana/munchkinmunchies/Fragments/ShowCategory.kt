package com.asthana.munchkinmunchies.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asthana.munchkinmunchies.Adapters.ItemCardModel
import com.asthana.munchkinmunchies.Adapters.Recipe
import com.asthana.munchkinmunchies.Adapters.RecipeAdapter
import com.asthana.munchkinmunchies.R
import com.asthana.munchkinmunchies.databinding.FragmentShowCategoryBinding
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class ShowCategory : Fragment() , RecipeAdapter.OnItemClickListener {

    lateinit var binding: FragmentShowCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding = FragmentShowCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.showitems)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecipeAdapter(emptyList(), this)
        recyclerView.adapter = adapter



        binding.progresslinear.visibility = View.VISIBLE

        val tabName = arguments?.getString("1")
        Log.d("tabu", tabName.toString())
        if(tabName == "ketogenic"){
            fetchDataFromAPI()
        }
        if(tabName == "vegantab"){
            veganAPI()
        }
        if(tabName == "saladtab"){
            saladDataAPI()
        }
        if(tabName == "smoothie"){
            smoothietab()
        }
        if(tabName == "energytab"){
            energytab()
        }
        if(tabName == "vegtab"){
            vegtabAPI()
        }
        if(tabName == "quicktab"){
            quickAPI()
        }
        if(tabName == "rollstab"){
            wrapAPI()
        }
        if(tabName == "caketab"){
            cakeAPI()
        }
        if(tabName == "glutentab"){
            glutenAPI()
        }
        if(tabName == "deserttab"){
            desertAPI()
        }
        if(tabName == "smoothietab"){
            smoothietab()
        }
        if(tabName == "pizzatab"){
            pizzaAPI()
        }
        if(tabName == "burgertab"){
            burgerAPI()
        }
    }

    private fun veganAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=vegan&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    private fun saladDataAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=salad&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    private fun smoothietab() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=smoothie&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun pizzaAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=pizza&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    private fun burgerAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=burger&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun energytab() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=energy&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    private fun vegtabAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=veg&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun quickAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=quick&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun wrapAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=wraps&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    //Home Fragment
    private fun cakeAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=cake&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }

    private fun glutenAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=glutenfree&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun desertAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=deserts&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }
    private fun fetchDataFromAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://edamam-recipe-search.p.rapidapi.com/search?q=keto&from=0&to=50")
            .get()
            .addHeader("X-RapidAPI-Key", "__")
            .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Parse the response data here and convert it to a list of Recipe objects
                Log.d("adap", responseData.toString())

                val recipes = parseResponseData(responseData)
                // Update the UI on the main thread
                requireActivity().runOnUiThread {
                    binding.progresslinear.visibility = View.GONE
                    adapter.setData(recipes)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure here
            }
        })
    }


    private fun parseResponseData(responseData: String?): List<Recipe> {
        val recipes = mutableListOf<Recipe>()

        responseData?.let {
            try {
                val jsonObject = JSONObject(it)
                val hitsArray = jsonObject.getJSONArray("hits")

                for (i in 0 until hitsArray.length()) {
                    val hitObject = hitsArray.getJSONObject(i)
                    val recipeObject = hitObject.getJSONObject("recipe")
                    val title = recipeObject.getString("label")
                    val imageUrl = recipeObject.getString("image")
                    // Get the calorie value as a string
                    val calorieString = recipeObject.getString("calories")
                    val calorie = calorieString.toDouble()
                    val formattedCalorie = String.format(Locale.US, "%.2f", calorie)

                    // Retrieve the ingredient lines array
                    val ingredientLinesArray = recipeObject.getJSONArray("ingredientLines")
                    val ingredientLines = mutableListOf<String>()

                    for (j in 0 until ingredientLinesArray.length()) {
                        val ingredientLine = ingredientLinesArray.getString(j)
                        ingredientLines.add(ingredientLine)
                    }

                    // Exclude recipe if title contains specific keywords
                    if (title.contains("meats", ignoreCase = true) || title.contains("cow", ignoreCase = true) ||
                        title.contains("chicken", ignoreCase = true) || title.contains("buffalo", ignoreCase = true) ||
                        title.contains("beef", ignoreCase = true) || title.contains("shrimp", ignoreCase = true)) {
                        continue
                    }

                    val healthLabelsArray = recipeObject.getJSONArray("cuisineType")
                    val healthLabels = mutableListOf<String>()

                    healthLabelsArray?.let {
                        for (j in 0 until it.length()) {
                            val cuisineType = it.getString(j)
                            val extractedCuisineTypes = cuisineType.split(",").map { it.trim() }
                            healthLabels.addAll(extractedCuisineTypes)
                        }
                    }

                    // Retrieve the ingredients array
                    val ingredientsArray = recipeObject.getJSONArray("ingredients")
                    var excludeRecipe = false
                    var foodCategory = ""
                    for (j in 0 until ingredientsArray.length()) {
                        val ingredientObject = ingredientsArray.getJSONObject(j)
                        foodCategory = ingredientObject.optString("foodCategory", "")

                        // Exclude if "meats," "cow," "chicken," or "buffalo" keyword is present in foodCategory
                        if (foodCategory.contains("meats", ignoreCase = true) || foodCategory.contains("cow", ignoreCase = true) ||
                            foodCategory.contains("chicken", ignoreCase = true) || foodCategory.contains("Buffalo", ignoreCase = true) ||
                            foodCategory.contains("beef", ignoreCase = true)) {
                            excludeRecipe = true
                            break
                        }
                    }

                    if (!excludeRecipe) {
                        val recipe = Recipe(title, imageUrl, healthLabels, formattedCalorie,
                            ingredientLines as ArrayList<String>
                        )
                        recipes.add(recipe)
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return recipes
    }



    override fun onItemClick(recipe: Recipe) {
        // Handle the item click here
        // Open the RecipeFragment and pass the recipe details as arguments
        val recipeFragment = Dishdetail()

        val bundle = Bundle()
        bundle.putString("title", recipe.label)
        bundle.putString("imageUrl", recipe.image)
        bundle.putString("calories", recipe.calories)
        // ... Add other necessary data to the bundle
        bundle.putStringArrayList("ingredientLines", recipe.ingredientLines)
        recipeFragment.arguments = bundle

        // Use a FragmentManager to display the RecipeFragment
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.framefrag, recipeFragment)
            .addToBackStack(null)
            .commit()
    }
}

