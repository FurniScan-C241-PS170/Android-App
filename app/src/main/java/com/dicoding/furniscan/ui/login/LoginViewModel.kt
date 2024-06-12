package com.dicoding.furniscan.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authRepository: AuthRepository,
    private val preferences: TokenPreferences
): ViewModel() {
    fun login(emailInput: String, passwordInput: String) = authRepository.login(emailInput, passwordInput)


    fun saveState(token: String) {
        viewModelScope.launch {
            preferences.saveToken(token)
            preferences.login()
        }
    }
}