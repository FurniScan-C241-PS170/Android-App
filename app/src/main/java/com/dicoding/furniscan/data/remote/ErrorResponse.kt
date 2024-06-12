package com.dicoding.furniscan.data.remote

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
