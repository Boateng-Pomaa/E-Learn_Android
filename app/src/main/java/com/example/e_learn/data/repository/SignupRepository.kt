package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.SignUpData
import com.example.e_learn.data.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class SignupRepository(private val apiList:ApiList) {

    fun signUp(signupData: SignUpData) : Call<SignUpResponse> {
        return apiList.signUp(signupData)
    }
}