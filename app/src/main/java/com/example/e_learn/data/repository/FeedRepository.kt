package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.FeedResponse
import retrofit2.Call

class FeedRepository(private val apilist: ApiList) {
    fun getFeed(): Call<FeedResponse> {
        return apilist.getFeed()
    }

    fun search(searchReq: String): Call<FeedResponse>{
        return apilist.search(searchReq)
    }
}