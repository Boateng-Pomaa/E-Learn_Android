package com.example.e_learn.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.SignUpData
import com.example.e_learn.data.model.SignUpResponse
import com.example.e_learn.data.repository.SignupRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel(private val signupRepo:SignupRepository):ViewModel() {
    private val _signUpResult = MutableLiveData<Resource<SignUpResponse>>()
    val signUpResult: LiveData<Resource<SignUpResponse>> = _signUpResult

    fun signUp(username: String, password: String, email: String) {
        _signUpResult.value = Resource.loading()
       viewModelScope.launch {
           try {
               val signUpData = SignUpData(username,password,email)
               signupRepo.signUp(signUpData).enqueue(object : Callback<SignUpResponse> {
                   override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                       if(response.isSuccessful){

                           val data = response.body() ?: throw IllegalStateException("Post cannot be null")
                           _signUpResult.value = Resource.success(data)
                       }
                       else{
                           val errorMessage = response.errorBody()?.string() ?:response.message()
                           _signUpResult.value = Resource.error(errorMessage)
                       }
                   }

                   override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                       _signUpResult.value = t.message?.let { Resource.error(it) }
                   }

               })
           }catch (e:Exception){
               print(e)
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SignUpViewModel", "SignUpViewModel destroyed!")
    }
}