package com.example.e_learn.ui.login.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.UserQuestionResponse
import com.example.e_learn.data.repository.UserQuestionsRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsViewModel (private val UQRepo: UserQuestionsRepository) : ViewModel() {
    private val _feeds = MutableLiveData<Resource<UserQuestionResponse>>()
    val feeds: LiveData<Resource<UserQuestionResponse>> get()  = _feeds


    fun getUserQuestions(userId: String) {
        _feeds.value = Resource.loading()
        viewModelScope.launch {
            try {
                UQRepo.userQuestions(userId).enqueue(object : Callback<UserQuestionResponse> {
                    override fun onResponse(
                        call: Call<UserQuestionResponse>,
                        response: Response<UserQuestionResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            _feeds.value = Resource.success(data)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _feeds.value = Resource.error( errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<UserQuestionResponse>, t: Throwable) {
                        _feeds.value = t.message?.let { Resource.error(it) }
                        t.printStackTrace()
                    }
                })
            }catch (e:Exception){
                Log.e("error", e.toString())
                Log.i("QuestionsViewModel", "Something went wrong!")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuestionsViewModel", "QuestionsViewModel destroyed!")
    }
}