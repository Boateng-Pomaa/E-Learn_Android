package com.example.e_learn.data.model

class LoginRequest(
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
)

annotation class SerializedName(val value: String)
