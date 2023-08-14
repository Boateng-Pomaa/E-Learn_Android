package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.PostRequest
import com.example.e_learn.data.model.PostResponse
import retrofit2.Call

class PostRepository(private val apilist:ApiList) {
    fun postQuestion(userId: String, postReq:PostRequest): Call<PostResponse>{
        return apilist.postQuestion(userId,postReq)
    }
}