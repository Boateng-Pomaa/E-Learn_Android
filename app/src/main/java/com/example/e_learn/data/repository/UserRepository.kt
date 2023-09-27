package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.PasswordRequest
import com.example.e_learn.data.model.RequestResponse
import com.example.e_learn.data.model.ResetResponse
import com.example.e_learn.data.model.UserResponse
import retrofit2.Call

class UserRepository (private val apilist: ApiList) {
     fun getUser(userId:String): Call<UserResponse> {
         return apilist.getUser(userId)
     }

    fun requestPassword(email:String):Call<RequestResponse>{
        return apilist.requestPassword(email)
    }

    fun resetPassword(userId:String,token:String,passReq:PasswordRequest):Call<ResetResponse>{
        return apilist.resetPassword(userId,token,passReq)
    }
}