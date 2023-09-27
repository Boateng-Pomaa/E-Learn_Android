package com.example.e_learn.data.model

data class PasswordRequest(@SerializedName("password")var password:String)


data class  Verify( val Token:String,val Id:String)
