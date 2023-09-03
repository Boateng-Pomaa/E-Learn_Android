package com.example.e_learn.ui.login.ui.PostQuestion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_learn.data.model.FeedModel
import com.example.e_learn.data.model.PostRequest
import com.example.e_learn.data.model.PostResponse
import com.example.e_learn.data.repository.PostRepository
import com.example.e_learn.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel (private val postRepo:PostRepository) : ViewModel() {
    private val _postResult = MutableLiveData<Resource<FeedModel>>()
    val postResult: LiveData<Resource<FeedModel>> = _postResult

    fun postQuestion(userId: String, title:String, question:String){
        _postResult.value = Resource.loading()
        viewModelScope.launch {
            try {
                val postData = PostRequest(title,question)
                postRepo.postQuestion(userId,postData).enqueue(object : Callback<PostResponse> {
                    override fun onResponse(
                        call: Call<PostResponse>,
                        response: Response<PostResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.posts!!
                            _postResult.value = Resource.success(data)
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            _postResult.value = Resource.error( errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        _postResult.value = t.message?.let { Resource.error(it) }
                    }

                })
            }catch (e:Exception){
                print(e)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GalleryViewModel", "GalleryViewModel destroyed!")
    }
}