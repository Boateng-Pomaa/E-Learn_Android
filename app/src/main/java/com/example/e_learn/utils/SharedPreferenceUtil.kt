package com.example.e_learn.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context)  {
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE)
    fun saveData(key:String,value:String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun retrieveData(key:String): String? {
        return sharedPreferences.getString(key, null)
    }


}