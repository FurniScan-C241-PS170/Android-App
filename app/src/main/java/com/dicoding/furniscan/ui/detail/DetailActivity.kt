package com.dicoding.furniscan.ui.detail

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.furniscan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llExpandableItem.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)
        binding.expandable.setOnClickListener {
            val v = if(binding.tvExpandableItem.visibility == View.GONE) View.VISIBLE else View.GONE
            binding.tvExpandableItem.visibility = v
        }
    }
}