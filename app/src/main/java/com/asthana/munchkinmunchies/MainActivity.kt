package com.asthana.munchkinmunchies

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.asthana.munchkinmunchies.Fragments.AllDetails
import com.asthana.munchkinmunchies.Fragments.Category
import com.asthana.munchkinmunchies.Fragments.Home
import com.asthana.munchkinmunchies.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomnav.itemIconTintList = null
        binding.bottomnav.isItemHorizontalTranslationEnabled = false

        val homescreen = Home()
        supportFragmentManager.beginTransaction().apply {
            replace(binding.framefrag.id, homescreen)
            commit()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnav)
        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener
        OnNavigationItemSelectedListener@{
            when(it.itemId){
                    R.id.homi-> {
                    val fragmentInstance = supportFragmentManager.findFragmentById(R.id.framefrag)
                    if (fragmentInstance is Home) {
                        return@OnNavigationItemSelectedListener false
                    } else {
                        setMainFragment(Home())
//                        bottomNavigationView.setBackgroundColor(
//                            ContextCompat.getColor(
//                                getApplicationContext(),
//                                R.color.white
//                            )
//                        )
                        return@OnNavigationItemSelectedListener true
                    }
                }
                R.id.cats-> {
                    val fragmentInstance = supportFragmentManager.findFragmentById(R.id.framefrag)
                    if (fragmentInstance is Category) {
                        return@OnNavigationItemSelectedListener false
                    } else {
                        setCurrentFragment(Category())
//                        bottomNavigationView.setBackgroundColor(
//                            ContextCompat.getColor(
//                                getApplicationContext(),
//                                R.color.white
//
//                            )
//                        )
                        return@OnNavigationItemSelectedListener true
                    }
                }
//                R.id.riddles-> {
//                    val fragmentInstance = supportFragmentManager.findFragmentById(R.id.framefrag)
//                    if (fragmentInstance is Riddles) {
//                        return@OnNavigationItemSelectedListener false
//                    } else {
//                        setCurrentFragment(Riddles())
//                        bottomNavigationView.setBackgroundColor(
//                            ContextCompat.getColor(
//                                getApplicationContext(),
//                            )
//                        )
//                        return@OnNavigationItemSelectedListener true
//                    }
//                }
                else ->
                    return@OnNavigationItemSelectedListener false
                //                R.id.settings->setCurrentFragment(thirdFragment)
            }
        })
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framefrag, fragment).addToBackStack(null)
                .commit()
        }

    private fun setMainFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framefrag, fragment)
                .commit()
        }
}