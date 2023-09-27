package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.*
import retrofit2.Call

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(private val apiList: ApiList) {
   fun doLogin(loginReq: LoginRequest):Call<LoginResponse>{
        return apiList.doLogin(loginReq)
    }
}

