package com.example.myretrfit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerCreate {
    private const val BASE_URL = "http://172.0.0.1"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //    更简洁的写法·
    inline fun <reified T> create(): T = create(T::class.java)
}