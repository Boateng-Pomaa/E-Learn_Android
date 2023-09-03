package com.example.e_learn.ui.login.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_learn.data.model.FeedModel

class SharedViewModel: ViewModel() {
    private val _sharedData = MutableLiveData<FeedModel>()
    val sharedData:LiveData<FeedModel> get() = _sharedData

    fun setSharedData(data:FeedModel){
        _sharedData.value = data
    }

}