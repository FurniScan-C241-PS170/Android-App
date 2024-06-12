package com.dicoding.furniscan.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("users/signup")
    suspend fun signup(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): LoginResult

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResult
}