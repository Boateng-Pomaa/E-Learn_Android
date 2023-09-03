package com.example.e_learn.data.api


import com.example.e_learn.data.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiList {
    //TODO : Login User
    @POST("/login")
    fun doLogin(@Body loginReq:LoginRequest): Call<LoginResponse>

    //TODO : Get Student
    @GET("/profile/{id}")
    @Headers("Accept:application/json","Content-Type:application/json")
     fun getUser(@Path("id")userId:String): Call<UserResponse>

     //user Questions
     @GET("/yourQuestions/{id}")
     @Headers("Accept:application/json","Content-Type:application/json")
     fun userQuestions(@Path("id")userId:String):Call<UserQuestionResponse>

     //adding answers
     @POST("/answer/{id}")
     fun answer(@Path("id")userId:String,
                @Query("questionId") questionId:String,
                @Body ansReq:AnswerRequest):Call<AnswerResponse>


    @GET("/viewAnswers/{questionId}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getAnswer(@Path("questionId")questionId: String):Call<AnswerResponse>

//     //voteCount
//     @POST("/upvote")
//     fun updateVote(@Query("answerId") answerId:String,@Query("isUpvote") isUpvote:Boolean)
     
//     //user Answers
//     @GET("/yourAnswers")
//     @Headers("Accept:application/json","Content-Type:application/json")
//     fun userAnswers(@Path("id")userId:String): Call<userAnswersResponse>

     //searching
     @GET("/search")
     @Headers("Accept:application/json","Content-Type:application/json")
     fun search(@Query("keyword") keyword:String):Call<FeedResponse>

     @GET("/feed")
     @Headers("Accept:application/json","Content-Type:application/json")
      fun getFeed():Call<FeedResponse>

    @POST("/signup")
     fun signUp(@Body signupData:SignUpData): Call<SignUpResponse>


    @POST("/post/{username}")
    fun postQuestion(@Path("username")username:String,@Body postReq:PostRequest):Call<PostResponse>

    companion object {
        private const val TAG = "--ApiService  192.168.0.101,  10.225.0.245"

        private const val BASE_URL = "http://192.168.0.101:5000"

        fun create(): ApiList {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(ApiWorker.client)
                .build()
            return retrofit.create(ApiList::class.java)
        }
    }
}