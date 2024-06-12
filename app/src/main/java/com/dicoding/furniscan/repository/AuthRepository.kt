package com.dicoding.furniscan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.furniscan.data.remote.ApiService
import com.dicoding.furniscan.data.remote.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import com.dicoding.furniscan.Result
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.remote.LoginResult

class AuthRepository (
    private var apiService: ApiService,
    private val pref: TokenPreferences,

) {
    fun register(name: String, email: String, password: String, repassword: String): LiveData<Result<LoginResult>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.signup(name, email, password, repassword)
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val error =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun login(email: String, password: String): LiveData<Result<LoginResult>> = liveData {
        emit(Result.Loading)
            try {
                val response = apiService.login(email, password)
                val user = response
                if (user != null) {
                    emit(Result.Success(user))
                } else {
                    emit(Result.Error("There was an error"))
                }
            } catch (e: HttpException) {
                val error =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(
            apiService: ApiService,
            pref: TokenPreferences
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService, pref).also { instance = it }
            }
    }

}