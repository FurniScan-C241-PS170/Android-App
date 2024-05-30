package com.dicoding.furniscan.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.ActivityOnBoardingBinding
import com.dicoding.furniscan.ui.welcome.WelcomeActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.hide()



        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

    }
}