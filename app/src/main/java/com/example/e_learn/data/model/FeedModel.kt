package com.example.e_learn.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedModel(

    @SerializedName("_id") var _id:String,
    @SerializedName("username") var username:String,
    @SerializedName("title")  var title:String,
    @SerializedName("question") var question:String,
    @SerializedName("createdAt") var createdAt:String
) : Parcelable
