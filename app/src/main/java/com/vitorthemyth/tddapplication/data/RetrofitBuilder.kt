package com.vitorthemyth.tddapplication.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://run.mocky.io/v3/"


object RetrofitBuilder {

    // Create an OkHttp client
    private val httpClient = OkHttpClient.Builder()

    // Create a logging interceptor and set the desired log level
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    init {
        // Add the logging interceptor to the OkHttp client
        httpClient.addInterceptor(loggingInterceptor)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
    }

    fun getInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
