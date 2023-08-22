package com.asthana.munchkinmunchies.Fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.viewpager2.widget.ViewPager2
import com.asthana.munchkinmunchies.Adapters.SliderAdapter
import com.asthana.munchkinmunchies.R
import com.asthana.munchkinmunchies.databinding.FragmentCategoryBinding
import com.asthana.munchkinmunchies.databinding.FragmentHomeBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Category : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager2 = binding.viewPagerSlider


        val imageList = listOf(
            R.drawable.view,
            R.drawable.vew,
            R.drawable.view,
            R.drawable.vie
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
                animateZoomIn(viewPager.getChildAt(0))

            }
        })

        sliderHandler.postDelayed(runnable, delayInMillis)

        //Navigate Fragment Tabs
        binding.ketogenicTab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                    "ketogenic") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
       }

        binding.vegantab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1", "vegantab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.saladtab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "saladtab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.smoothietab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "smoothie") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.energytab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "energytab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.proteinTab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "vegtab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.quicktab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "quicktab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }

        binding.rollsTab.setOnClickListener {
            val breakfastview = ShowCategory()
            val args = Bundle()
            args.putString("1",
                "rollstab") // Pass the name of the clicked tab ID here
            breakfastview.arguments = args
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.framefrag,breakfastview).commit()
        }
    }

    private fun animateZoomIn(view: View) {
        view.scaleX = 0.85f
        view.scaleY = 0.85f
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .setInterpolator(OvershootInterpolator())
            .start()
    }

}