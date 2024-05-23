package com.dicoding.furniscan.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.furniscan.databinding.ActivityWelcomeBinding
import com.dicoding.furniscan.ui.login.LoginActivity
import com.dicoding.furniscan.ui.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            loginButton.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            }

            signupButton.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, SignupActivity::class.java))
            }
        }
    }
}