package com.example.e_learn.data.model

data class AnswerModel(
    @SerializedName("_id") var _id:String,
    @SerializedName("username") var username:String,
    @SerializedName("content") var content:String,
    @SerializedName("questionId") var questionId:String,
    @SerializedName("upvote") var upvote:Int,
    @SerializedName("createdAt") var createdAt:String
)
