package com.dicoding.furniscan.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.ActivityWelcomeBinding
import com.dicoding.furniscan.ui.login.LoginActivity
import com.dicoding.furniscan.ui.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        supportActionBar?.hide()

        playAnimation()

        binding.apply {
            loginButton.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            }

            signupButton.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, SignupActivity::class.java))
            }
        }
    }

    private fun playAnimation() {
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.tvDescription, View.ALPHA, 1f).setDuration(500)
        val image = ObjectAnimator.ofFloat(binding.ivWelcome, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(signup, login )
        }

        AnimatorSet().apply {
            playSequentially(title, desc,image, together)
            start()
        }
    }
}