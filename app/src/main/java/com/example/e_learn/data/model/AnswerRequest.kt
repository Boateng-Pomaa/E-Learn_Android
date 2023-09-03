package com.example.e_learn.data.model

data class AnswerRequest(
    @SerializedName("content") var content:String,
//    @SerializedName("img") var img:ByteArray
) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as AnswerRequest
//
//        if (content != other.content) return false
//        if (!img.contentEquals(other.img)) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = content.hashCode()
//        result = 31 * result + img.contentHashCode()
//        return result
//    }
}
