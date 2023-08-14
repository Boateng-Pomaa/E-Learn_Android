package com.example.e_learn.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.e_learn.data.model.LoginResponse
import com.example.e_learn.data.model.User
import com.google.gson.Gson

object SharedPreferenceUtil  {

    fun getSavedResponse(context: Context): LoginResponse? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val json = sharedPreferences.getString("user_data", null)
        return if (json != null) {
            Gson().fromJson(json, LoginResponse::class.java)
        } else {
            null
        }
    }

    fun getUserData(context: Context): User {
        val savedResponse = getSavedResponse(context)
            return savedResponse!!.userdata

    }
//     fun saveResponse(data: LoginResponse) {
//        val json = Gson().toJson(data)
//        val editor = sharedPreferences.edit()
//        editor.putString("user_data", json)
//        editor.apply()
//    }
//    fun saveData(key:String,value:String){
//        val editor = sharedPreferences.edit()
//        editor.putString(key,value)
//        editor.apply()
//    }
//
//    fun retrieveData(key:String): String? {
//        return sharedPreferences.getString(key, null)
//    }


}