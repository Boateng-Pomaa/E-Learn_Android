package com.example.e_learn.data.model

data class SaveScoreResponse(val message:String)
data class GetScoreResponse(val message: String,val Score:List<ScoreModel>)

data class ScoreModel(val quiz:String,val score:Int)