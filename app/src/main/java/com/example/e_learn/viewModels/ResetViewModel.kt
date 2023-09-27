package com.example.e_learn.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.PasswordRequest
import com.example.e_learn.data.model.ResetResponse
import com.example.e_learn.data.repository.UserRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _results = MutableLiveData<Resource<ResetResponse>>()
    val results: LiveData<Resource<ResetResponse>> get() = _results

    fun resetPassword(userId:String,token:String,passReq:String){
        _results.value = Resource.loading()
        viewModelScope.launch {
            try {
                val data = PasswordRequest(passReq)
                userRepository.resetPassword(userId,token,data).enqueue(object :
                    Callback<ResetResponse> {
                    override fun onResponse(
                        call: Call<ResetResponse>,
                        response: Response<ResetResponse>
                    ) {
                        if(response.isSuccessful){
                            val user = response.body()!!
                            Log.i("RESPONSE",user.toString())
                            _results.value = Resource.success(user)
                        }
                        else{
                            val errorMessage = response.errorBody()?.string() ?:response.message()
                            _results.value = Resource.error(errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<ResetResponse>, t: Throwable) {
                        _results.value = t.message?.let { Resource.error(it) }
                    }

                })
            }catch (e:Exception){
                print(e)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("ResetViewModel", "ResetViewModel destroyed!")
    }

}