package com.dicoding.furniscan.ui.login

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
import com.dicoding.furniscan.databinding.ActivityLoginBinding
import com.dicoding.furniscan.ui.main.MainActivity
import com.dicoding.furniscan.Result

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.progressBar.visibility = View.GONE

        supportActionBar?.hide()

        playAnimation()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val factory: ViewModelFactory =
            ViewModelFactory.getInstance(
                this,
                TokenPreferences.getInstance(dataStore)
            )
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        setupView()
        setupAction()
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(250)
        val descEmail = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(250)
        val inputEmail = ObjectAnimator.ofFloat(binding.tilEmail, View.ALPHA, 1f).setDuration(250)
        val descPassword = ObjectAnimator.ofFloat(binding.password, View.ALPHA, 1f).setDuration(250)
        val inputPassword =
            ObjectAnimator.ofFloat(binding.tilPassword, View.ALPHA, 1f).setDuration(250)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(250)

        AnimatorSet().apply {
            playSequentially(title, descEmail, inputEmail, descPassword, inputPassword, login)
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
        binding.btnLogin.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()
            viewModel.login(email, password).observe(this) { response ->
                when (response) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = response.data
                        viewModel.saveState(data.data?.token.toString())
                        AlertDialog.Builder(this).apply {
                            setTitle("Success!")
                            setMessage(getString(R.string.login_success))
                            setPositiveButton(getString(R.string.continue_dialog)) { _, _ ->
                                Intent(this@LoginActivity, MainActivity::class.java).apply {
                                    flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(this)
                                }
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
                            setPositiveButton(getString(R.string.continue_dialog)) { _, _ -> }
                            create()
                            show()
                        }
                    }
                }
            }
        }
    }


}