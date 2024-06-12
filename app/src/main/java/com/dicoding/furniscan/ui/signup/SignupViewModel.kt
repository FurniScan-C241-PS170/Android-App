package com.dicoding.furniscan.ui.signup

import androidx.lifecycle.ViewModel
import com.dicoding.furniscan.repository.AuthRepository

class SignupViewModel (private val authRepository: AuthRepository) : ViewModel(){
    fun register(nameInput: String, emailInput: String, passwordInput: String,  repasswordInput: String) =
        authRepository.register(nameInput, emailInput, passwordInput, repasswordInput)
}