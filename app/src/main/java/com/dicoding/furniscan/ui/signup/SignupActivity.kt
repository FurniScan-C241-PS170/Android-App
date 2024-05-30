package com.dicoding.furniscan.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        supportActionBar?.hide()
        playAnimation()

    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 1f).setDuration(250)
        val descName = ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(250)
        val inputName = ObjectAnimator.ofFloat(binding.etlUsername, View.ALPHA, 1f).setDuration(250)
        val descEmail = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(250)
        val inputEmail = ObjectAnimator.ofFloat(binding.etlEmail, View.ALPHA, 1f).setDuration(250)
        val descPassword = ObjectAnimator.ofFloat(binding.password, View.ALPHA, 1f).setDuration(250)
        val inputPassword = ObjectAnimator.ofFloat(binding.etlPassword, View.ALPHA, 1f).setDuration(250)
        val descConfirmPassword = ObjectAnimator.ofFloat(binding.tvConfirmPassword, View.ALPHA, 1f).setDuration(250)
        val inputConfirmPassword = ObjectAnimator.ofFloat(binding.etlConfirmPassword, View.ALPHA, 1f).setDuration(250)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(250)

        AnimatorSet().apply {
            playSequentially( title,descName,inputName,descEmail, inputEmail, descPassword, inputPassword, descConfirmPassword, inputConfirmPassword, signup)
            start()
        }
    }
}