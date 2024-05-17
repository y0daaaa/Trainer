package com.example.trainer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.trainer.R
import com.example.trainer.databinding.ActivityMainBinding
import com.example.trainer.ui.home.HomeFragment
import com.example.trainer.ui.profile.ProfileFragment
import com.example.trainer.ui.statistics.StatisticsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.home_nav -> replaceFragment(HomeFragment())
                R.id.statistic_nav -> replaceFragment(StatisticsFragment())
                R.id.profile_nav -> replaceFragment(ProfileFragment())


                else -> {



                }

            }

            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }

}