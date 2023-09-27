package com.example.e_learn.data.api


import com.example.e_learn.data.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiList {
    @POST("/login")
    fun doLogin(@Body loginReq:LoginRequest): Call<LoginResponse>

    @GET("/profile/{id}")
    @Headers("Accept:application/json","Content-Type:application/json")
     fun getUser(@Path("id")userId:String): Call<UserResponse>

     //user Questions
     @GET("/userQuestions/{id}")
     @Headers("Accept:application/json","Content-Type:application/json")
     fun userQuestions(@Path("id")userId:String):Call<FeedResponse>

     @GET("/userAnswers/{id}")
     @Headers("Accept:application/json","Content-Type:application/json")
     fun userAnswers(@Path("id")userId:String):Call<AnswerResponse>

     //adding answers
     @POST("/answer/{id}")
     fun answer(@Path("id")userId:String,
                @Query("questionId") questionId:String,
                @Body ansReq:AnswerRequest):Call<AnswerResponse>


    @GET("/viewAnswers/{questionId}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getAnswer(@Path("questionId")questionId: String):Call<AnswerResponse>

    @POST ("/requestpassword/{email}")
    fun requestPassword(@Path("email")email: String):Call<RequestResponse>

    @POST("/resetPassword/{id}/{Token}")
    fun resetPassword(@Path("id") userId:String,
    @Path("Token") token:String, @Body passReq:PasswordRequest):Call<ResetResponse>

     //voteCount
     @POST("/upvote/{answerId}")
     fun upvote(@Path("answerId") answerId:String):Call<AnswerResponse>

     @POST("/downvote/{answerId}")
     fun downvote(@Path("answerId") answerId: String):Call<AnswerResponse>

     //searching
     @GET("/search")
     @Headers("Accept:application/json","Content-Type:application/json")
     fun search(@Query("keyword") keyword:String):Call<FeedResponse>

     @GET("/feed")
     @Headers("Accept:application/json","Content-Type:application/json")
      fun getFeed():Call<FeedResponse>

    @POST("/signup")
     fun signUp(@Body signupData:SignUpData): Call<SignUpResponse>

     @POST("/saveScore/{id}/{score}/{quiz}")
     fun saveScore(@Path("id")id:String, @Path("score")score:Int,@Path("quiz")quiz:String):Call<SaveScoreResponse>


    @POST("/post/{username}")
    fun postQuestion(@Path("username")username:String,@Body postReq:PostRequest):Call<PostResponse>

    companion object {
        private const val TAG = "--ApiService  192.168.0.101,  10.225.0.245"

        private const val BASE_URL = "https://elearn-hia3.onrender.com/"

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