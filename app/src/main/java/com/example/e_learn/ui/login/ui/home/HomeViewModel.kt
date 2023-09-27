package com.example.e_learn.ui.login.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome Back!\n Lets Do Hard Things!"
    }
    val text: LiveData<String> = _text
}