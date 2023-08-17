package com.example.e_learn.data.model

data class User(
    @SerializedName("_id") var _id: String,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("token") var token: String,
)
