package com.example.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single {

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        retrofit.create(CoursesApi::class.java)
    }
}