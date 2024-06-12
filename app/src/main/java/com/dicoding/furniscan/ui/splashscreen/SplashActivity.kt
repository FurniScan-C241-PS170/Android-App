package com.dicoding.furniscan.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.furniscan.R
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.preference.dataStore
import com.dicoding.furniscan.ui.main.MainActivity
import com.dicoding.furniscan.ui.onboarding.OnBoardingActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val userLogin = runBlocking {
                TokenPreferences.getInstance(this@SplashActivity.dataStore).getLoginStatus().first()
            }
            if (userLogin == true) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(applicationContext, OnBoardingActivity::class.java))
                finish()
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, SPLASH_TIME_OUT)
    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 3000
    }

}