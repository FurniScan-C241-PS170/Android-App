package com.dicoding.furniscan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.di.Injection
import com.dicoding.furniscan.repository.AuthRepository
import com.dicoding.furniscan.ui.Profile.ProfileViewModel
import com.dicoding.furniscan.ui.login.LoginViewModel
import com.dicoding.furniscan.ui.signup.SignupViewModel

class ViewModelFactory(
    private val repository: AuthRepository,
    private val preferences: TokenPreferences

) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                return SignupViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(repository, preferences) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                return ProfileViewModel(preferences) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context, preferences: TokenPreferences): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    preferences
                ).also { INSTANCE = it }
            }
        }
    }
}