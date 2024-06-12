package com.dicoding.furniscan.ui.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.furniscan.data.preference.TokenPreferences
import kotlinx.coroutines.launch

class ProfileViewModel(private val preferences: TokenPreferences) :
    ViewModel(){

    fun logout() {
        viewModelScope.launch {
            preferences.logout()
        }
    }
}