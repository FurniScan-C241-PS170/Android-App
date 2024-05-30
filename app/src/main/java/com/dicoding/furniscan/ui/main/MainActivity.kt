package com.dicoding.furniscan.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.ActivityMainBinding
import com.dicoding.furniscan.ui.favorite.FavoriteFragment
import com.dicoding.furniscan.ui.home.HomeFragment
import com.dicoding.furniscan.ui.scan.ScanActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainLayout, HomeFragment()).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val newFragment: Fragment = when (item.itemId) {
                R.id.item1 -> HomeFragment()
                R.id.item3 -> FavoriteFragment()
                else -> HomeFragment()
            }


            supportFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, newFragment)
                .commit()

            true
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

    }
}