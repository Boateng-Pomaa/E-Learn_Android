package com.example.e_learn.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.LoginRequest
import com.example.e_learn.data.model.LoginResponse
import com.example.e_learn.data.model.User
import com.example.e_learn.data.repository.LoginRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository ,application: Application) : AndroidViewModel(application) {
    private val _loginResult = MutableLiveData< Resource<User>>()
    val loginResult: LiveData<Resource<User>> get() = _loginResult
    fun doLogin(username:String,password:String) {
        _loginResult.value = Resource.loading()
        viewModelScope.launch {
            try {
                val loginData = LoginRequest(username,password)
                loginRepository.doLogin(loginData).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(response.isSuccessful){
                            val user = response.body()?.user!!
                            Log.i("RESPONSE",user.toString())
                            _loginResult.value = Resource.success(user)
                        }
                        else{
                            val errorMessage = response.errorBody()?.string() ?:response.message()
                            _loginResult.value = Resource.error(errorMessage)
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _loginResult.value = t.message?.let { Resource.error(it) }
                    }
                })
            }catch (e:Exception){
                Log.i("LoginViewModel", "something went wrong!")
                print(e)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "LoginViewModel destroyed!")
    }














   /* // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    */
}