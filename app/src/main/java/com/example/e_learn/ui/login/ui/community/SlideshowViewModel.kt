package com.example.e_learn.ui.login.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.FeedResponse
import com.example.e_learn.data.repository.FeedRepository
import com.example.e_learn.utils.Resource
import com.example.e_learn.utils.Resource.Companion.success
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SlideshowViewModel(private val feedRepo:FeedRepository) : ViewModel() {
    private val _feeds = MutableLiveData<Resource<FeedResponse>>()
    val feeds: LiveData<Resource<FeedResponse>> get()  = _feeds

    fun getFeed() {
        _feeds.value = Resource.loading()
        viewModelScope.launch {
            try {
                feedRepo.getFeed().enqueue(object : Callback<FeedResponse> {
                    override fun onResponse(
                        call: Call<FeedResponse>,
                        response: Response<FeedResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            _feeds.value = success(data)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _feeds.value = Resource.error( errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                        _feeds.value = t.message?.let { Resource.error(it) }
                        t.printStackTrace()
                    }
                })
            }catch (e:Exception){
                Log.e("error", e.toString())
                Log.i("FeedViewModel:getFeed()", "Something went wrong!")
            }
        }
    }

    fun search(title:String){
        _feeds.value = Resource.loading()
        viewModelScope.launch {
            try {
                feedRepo.search(title).enqueue(object :  Callback<FeedResponse>{
                    override fun onResponse(
                        call: Call<FeedResponse>,
                        response: Response<FeedResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            _feeds.value = success(data)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _feeds.value = Resource.error( errorMessage)
                        }
                    }
                    override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                        _feeds.value = t.message?.let { Resource.error(it) }
                        t.printStackTrace()
                    }
                })
            }catch (e:Exception){
                Log.e("error", e.toString())
                Log.i("FeedViewModel: search()", "Something went wrong!")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("SlideshowViewModel", "SlideshowViewModel destroyed!")
    }
}
