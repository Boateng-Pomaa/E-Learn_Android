package com.example.e_learn.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.PasswordRequest
import com.example.e_learn.data.model.RequestResponse
import com.example.e_learn.data.model.Verify
import com.example.e_learn.data.repository.UserRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _results = MutableLiveData<Resource<Verify>>()
    val result: LiveData<Resource<Verify>> get() = _results

    fun requestPassword(email: String) {
        _results.value = Resource.loading()
        viewModelScope.launch {
            try {
                userRepository.requestPassword(email).enqueue(object : Callback<RequestResponse> {
                    override fun onResponse(
                        call: Call<RequestResponse>,
                        response: Response<RequestResponse>
                    ) {
                        if (response.isSuccessful) {
                            val user = response.body()?.data!!
                            Log.i("RESPONSE", user.toString())
                            _results.value = Resource.success(user)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _results.value = Resource.error(errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<RequestResponse>, t: Throwable) {
                        _results.value = t.message?.let { Resource.error(it) }
                    }
                })
            } catch (e: Exception) {
                print(e)
            }
        }
    }


override fun onCleared() {
    super.onCleared()
    Log.i("PasswordViewModel", "PasswordViewModel destroyed!")
}
}