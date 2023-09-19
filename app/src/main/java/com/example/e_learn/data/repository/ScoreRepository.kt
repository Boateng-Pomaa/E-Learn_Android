package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.SaveScoreResponse
import retrofit2.Call

class ScoreRepository (private val apilist: ApiList){
    fun saveScore(id:String,score:Int,quiz:String): Call<SaveScoreResponse>{
        return apilist.saveScore(id,score,quiz)
    }
}