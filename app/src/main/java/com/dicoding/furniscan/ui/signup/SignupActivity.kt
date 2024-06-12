package com.dicoding.furniscan.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.R
import com.dicoding.furniscan.ViewModelFactory
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.preference.dataStore
import com.dicoding.furniscan.databinding.ActivitySignupBinding
import com.dicoding.furniscan.ui.welcome.WelcomeActivity
import com.dicoding.furniscan.Result

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.progressBar.visibility = View.GONE

        supportActionBar?.hide()
        playAnimation()

        val factory: ViewModelFactory =
            ViewModelFactory.getInstance(
                this,
                TokenPreferences.getInstance(dataStore)
            )
        viewModel = ViewModelProvider(this, factory)[SignupViewModel::class.java]

        setupView()
        setupAction()

    }

    private fun playAnimation() {
        val image = ObjectAnimator.ofFloat(binding.ivSignup, View.ALPHA, 1f).setDuration(500)
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
            playSequentially( image, title, descName,inputName,descEmail, inputEmail, descPassword, inputPassword, descConfirmPassword, inputConfirmPassword, signup)
            start()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            val name = binding.edSignupUsername.text.toString()
            val email = binding.edSignupEmail.text.toString()
            val password = binding.edSignupPassword.text.toString()
            val repassword = binding.edSignupConfirm.text.toString()
            viewModel.register(name, email, password, repassword).observe(this) { response ->
                when (response) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        AlertDialog.Builder(this).apply {
                            setTitle("Success!")
                            setMessage(getString(R.string.signup_success))
                            setPositiveButton("OK") { _, _ ->
                                startActivity(
                                    Intent(
                                        this@SignupActivity,
                                        WelcomeActivity::class.java
                                    )
                                )
                            }
                            create()
                            show()
                        }
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        AlertDialog.Builder(this).apply {
                            setTitle("Error!")
                            setMessage(response.error)
                            setPositiveButton("Try Again") { _, _ -> }
                            create()
                            show()
                        }
                    }

                }
            }
        }
    }
}