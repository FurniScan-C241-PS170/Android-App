package com.dicoding.furniscan.di

import android.content.Context
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.preference.dataStore
import com.dicoding.furniscan.data.remote.ApiConfig
import com.dicoding.furniscan.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AuthRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val token = runBlocking {
            pref.getToken().first()
        }
        val apiService = ApiConfig.getApiConfig(token.toString())

        return AuthRepository.getInstance(apiService, pref)
    }
}