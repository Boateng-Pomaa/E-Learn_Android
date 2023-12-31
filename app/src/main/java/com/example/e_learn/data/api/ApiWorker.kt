package com.example.e_learn.data.api


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object ApiWorker {
    private var mClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null

    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */
    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
//                I will remove the interceptor and logger
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(3000, TimeUnit.SECONDS)
                    .readTimeout(3000, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)  /// show all JSON in logCat
                    .addInterceptor { chain ->
                        val original = chain.request()

                        val requestBuilder = original.newBuilder()
//                        .addHeader("Authorization", AUTH)
                            .addHeader("Content-Type", "application/json")
                            .method(original.method(), original.body())

                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                mClient = httpBuilder.build()

            }
            return mClient!!
        }


    val gsonConverter: GsonConverterFactory
        get() {
            if (mGsonConverter == null) {
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create()
                    )
            }
            return mGsonConverter!!
        }
}