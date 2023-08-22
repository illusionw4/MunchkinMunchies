package com.asthana.munchkinmunchies.Fragments


import com.asthana.munchkinmunchies.Adapters.BannerModel
import com.asthana.munchkinmunchies.Adapters.BaseItem
import com.asthana.munchkinmunchies.Adapters.ItemCardContainer
import com.asthana.munchkinmunchies.Adapters.ItemCardModel
import com.asthana.munchkinmunchies.Adapters.MultiviewAdapter
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.asthana.munchkinmunchies.Adapters.SliderAdapter
import com.asthana.munchkinmunchies.R
import com.asthana.munchkinmunchies.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mfirestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MultiviewAdapter
    private val items: MutableList<BaseItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mfirestore = FirebaseFirestore.getInstance()

        recyclerView = view.findViewById(R.id.sliderecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MultiviewAdapter(items)
        recyclerView.adapter = adapter

        binding.animationView.visibility = View.VISIBLE
        binding.sliderecycler.visibility = View.GONE

        val viewPager: ViewPager2 = binding.viewPagerSlider
        val imageList = listOf(
            R.drawable.banne,
            R.drawable.banner,
            R.drawable.banne,
            R.drawable.banner
        )
        val sliderAdapter = SliderAdapter(imageList)
        viewPager.adapter = sliderAdapter

        binding.indicator.setViewPager(viewPager)

        val sliderHandler = Handler()
        val delayInMillis: Long = 3000

        val runnable = Runnable {
            val currentItem = viewPager.currentItem
            val totalItems = viewPager.adapter?.itemCount ?: 0
            val nextItem = if (currentItem < totalItems - 1) currentItem + 1 else 0
            viewPager.setCurrentItem(nextItem, true)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                sliderHandler.removeCallbacks(runnable)
                sliderHandler.postDelayed(runnable, delayInMillis)
            }
        })

        sliderHandler.postDelayed(runnable, delayInMillis)
        binding.animationView.visibility = View.VISIBLE
        binding.progtxt.visibility = View.VISIBLE

        binding.sliderecycler.visibility = View.GONE

        //Assign data
        binding.cake.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "caketab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.nogluten.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "glutentab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.deserts.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "deserttab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.smoothie.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "smoothietab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.pizza.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "pizzatab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.burger.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "burgertab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.salads.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "saladtab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

   fetchData()
}

    private fun fetchData() {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("DishItems")
        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                items.clear()
                for (documentSnapshot in querySnapshot) {
                    val bannerData = documentSnapshot.get("banner") as? Map<String, Any>
                    if (bannerData != null) {
                        val imageUrl = bannerData["imageUrl"] as? String
                        val txt = bannerData["txtban"] as? String
                        val textcolor = bannerData["textcolor"] as? String

                        if (txt != null && textcolor != null) {
                            val bannerModel = imageUrl?.let { BannerModel(it, txt, textcolor) }
                            bannerModel?.let { items.add(it) }
                            Log.d("FETCH_DATA", "Banner: $bannerModel")
                        }
                    }

                    val itemCardData = documentSnapshot.get("itemcard") as? Map<String, Any>
                    if (itemCardData != null) {
                        val itemCardModels = mutableListOf<ItemCardModel>()
                        for (key in itemCardData.keys) {
                            val categoryItemData = itemCardData[key] as? Map<String, Any>
                            val dishtitle = categoryItemData?.get("dishtitle") as? String ?: ""
                            val author = categoryItemData?.get("author") as? String ?: ""
                            val stars = (categoryItemData?.get("stars") as? Double)?.toFloat() ?: 0.0f
                            val imageUrl = categoryItemData?.get("imageUrl") as? String ?: ""
                            val itemCardModel = ItemCardModel(dishtitle, author, stars, imageUrl)
                            itemCardModels.add(itemCardModel)
                            Log.d("FETCH_DATA", "ItemCard: $itemCardModel")
                        }
                        val itemCardContainer = ItemCardContainer(itemCardModels)
                        items.add(itemCardContainer)
                    }
                }

                adapter.notifyDataSetChanged()
                Log.d("FETCH_DATA", "Data fetched successfully. Item count: ${items.size}")
                binding.animationView.visibility = View.GONE
                binding.progtxt.visibility = View.GONE

                binding.sliderecycler.visibility = View.VISIBLE

            }
            .addOnFailureListener { exception ->
                Log.e("FETCH_DATA", "Failed to fetch data: ${exception.message}")
                // Handle any errors that occur while fetching data
            }
    }



}

