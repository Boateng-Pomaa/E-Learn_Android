package com.example.e_learn.ui.login.ui.mathquizzes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_learn.data.model.GetScoreResponse
import com.example.e_learn.data.model.SaveScoreResponse
import com.example.e_learn.data.model.SignUpResponse
import com.example.e_learn.data.repository.ScoreRepository
import com.example.e_learn.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScoreViewModel(private val scoreRepo:ScoreRepository): ViewModel() {
    private val _saveResult = MutableLiveData<Resource<GetScoreResponse>>()
    val saveResult: LiveData<Resource<GetScoreResponse>> = _saveResult

    fun saveScore(id:String,score:Int,quiz:String){
        try {
            scoreRepo.saveScore(id,score,quiz).enqueue(object : Callback<SaveScoreResponse> {
                override fun onResponse(
                    call: Call<SaveScoreResponse>,
                    response: Response<SaveScoreResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: response.message()
                        _saveResult.value = Resource.error( errorMessage)
                    }
                }

                override fun onFailure(call: Call<SaveScoreResponse>, t: Throwable) {
                    _saveResult.value = t.message?.let { Resource.error(it) }
                }

            })
        }catch (e:Exception){
            print(e)
        }
    }

    fun getScore(id:String){
        try {
            scoreRepo.getScore(id).enqueue(object : Callback<GetScoreResponse> {
                override fun onResponse(
                    call: Call<GetScoreResponse>,
                    response: Response<GetScoreResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        _saveResult.value = Resource.success(data)
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: response.message()
                        _saveResult.value = Resource.error( errorMessage)
                    }
                }
                override fun onFailure(call: Call<GetScoreResponse>, t: Throwable) {
                    _saveResult.value = t.message?.let { Resource.error(it) }
                }

            })
        }catch (e:Exception){
            print(e)
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("ScoreViewModel", "ScoreViewModel destroyed!")
    }
}