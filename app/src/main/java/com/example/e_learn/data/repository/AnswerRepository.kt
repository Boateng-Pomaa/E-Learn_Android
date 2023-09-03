package com.example.e_learn.data.repository

import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.AnswerRequest
import com.example.e_learn.data.model.AnswerResponse
import retrofit2.Call

class AnswerRepository(private val apilist:ApiList) {
    fun answer(userId:String,questionId:String,answerReq:AnswerRequest): Call<AnswerResponse> {
        return apilist.answer(userId,questionId,answerReq)
    }

    fun getAnswers(questionId: String):Call<AnswerResponse>{
        return apilist.getAnswer(questionId)
    }
}