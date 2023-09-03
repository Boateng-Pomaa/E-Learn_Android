package com.example.e_learn.ui.login.ui.answer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.AnswerModel
import com.example.e_learn.data.model.AnswerRequest
import com.example.e_learn.data.model.AnswerResponse
import com.example.e_learn.data.repository.AnswerRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnswerViewModel (private val answerRepo:AnswerRepository): ViewModel() {
    private val _answerResult = MutableLiveData<Resource<AnswerResponse>>()
    val answerResult: LiveData<Resource<AnswerResponse>> = _answerResult

    fun answerPost(userId:String,questionId:String,content:String){
        _answerResult.value = Resource.loading()
        viewModelScope.launch {
            try {
                val answerData = AnswerRequest(content)
                answerRepo.answer(userId,questionId,answerData).enqueue(object : Callback<AnswerResponse>{
                    override fun onResponse(
                        call: Call<AnswerResponse>,
                        response: Response<AnswerResponse>
                    ) {
                        if (response.isSuccessful) {
                            val answerPosted = response.body()!!
                            _answerResult.value = Resource.success(answerPosted)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _answerResult.value = Resource.error( errorMessage)
                        }
                    }
                    override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                        _answerResult.value = t.message?.let { Resource.error(it) }
                    }
                })
            }catch (e:Exception){
                Log.i("AnswerView Model:", e.toString())
                print(e)
            }
        }
    }

    fun getAnswers(questionId: String){
        _answerResult.value = Resource.loading()
        viewModelScope.launch {
            try {
                answerRepo.getAnswers(questionId).enqueue(object :Callback<AnswerResponse>{
                    override fun onResponse(
                        call: Call<AnswerResponse>,
                        response: Response<AnswerResponse>
                    ) {
                        if (response.isSuccessful) {
                            val answerLoaded = response.body()!!
                            _answerResult.value = Resource.success(answerLoaded)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _answerResult.value = Resource.error( errorMessage)
                        }
                    }
                    override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                        _answerResult.value = t.message?.let { Resource.error(it) }
                    }
                })
            }catch (e:Exception){
                Log.i("AnswerViewMo getAnswer:", e.toString())
                print(e)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("AnswerViewModel", "AnswerViewModel destroyed!")
    }
}