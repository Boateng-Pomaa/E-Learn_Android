package com.example.e_learn.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context)  {
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE)
//      fun saveResponse(context: Context, data: String) {
//        val json = Gson().toJson(data)
//        val sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("user_data", json)
//        editor.apply()
//    }
//    private fun getSavedResponse(context: Context): LoginResponse? {
//        val sharedPreferences = context.getSharedPreferences("user_Details",Context.MODE_PRIVATE)
//        //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val json = sharedPreferences.getString("user_data", null)
//        return if (json != null) {
//            Gson().fromJson(json, LoginResponse::class.java)
//        } else {
//            null
//        }
//    }
//
//    fun getUserData(context: Context): User? {
//        val savedResponse = getSavedResponse(context)
//        return savedResponse?.user
//    }
//     fun saveResponse(data: LoginResponse) {
//        val json = Gson().toJson(data)
//        val editor = sharedPreferences.edit()
//        editor.putString("user_data", json)
//        editor.apply()
//    }
    fun saveData(key:String,value:String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun retrieveData(key:String): String? {
        return sharedPreferences.getString(key, null)
    }


}