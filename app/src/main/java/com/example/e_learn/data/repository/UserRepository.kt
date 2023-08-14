package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.UserResponse
import retrofit2.Call

class UserRepository (private val apilist: ApiList) {
     fun getUser(userId:String): Call<UserResponse> {
         return apilist.getUser(userId)
     }
}