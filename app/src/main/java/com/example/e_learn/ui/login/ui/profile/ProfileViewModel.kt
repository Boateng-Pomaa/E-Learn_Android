package com.example.e_learn.ui.login.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.UserResponse
import com.example.e_learn.data.repository.UserRepository
import com.example.e_learn.utils.Resource
import com.example.e_learn.utils.SharedPreferenceUtil
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val userRepo:UserRepository) : ViewModel() {
    private val _profile = MutableLiveData<Resource<UserResponse>>()
    val profile: LiveData<Resource<UserResponse>> get()  = _profile

    fun getUser(userId: String){
        _profile.value = Resource.loading()
        viewModelScope.launch {
            try {

                userRepo.getUser(userId).enqueue(object : Callback<UserResponse>{
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            _profile.value = Resource.success(data)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _profile.value = Resource.error( errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        _profile.value = t.message?.let { Resource.error(it) }
                        t.printStackTrace()
                    }

                })
            }catch (e:Exception){
                Log.e("error:", e.toString())
                Log.i("ProfileViewModel", "Something went wrong!")
            }
        }
    }
}