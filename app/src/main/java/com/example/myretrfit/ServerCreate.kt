package com.example.myretrfit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.suspendCoroutine

object ServerCreate {
    private const val BASE_URL = "http://172.0.0.1"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    //    更简洁的写法·
    inline fun <reified T> create(): T = create(T::class.java)
    suspend fun <T> Call<T>.wait(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {

                }

                override fun onFailure(call: Call<T>, t: Throwable) {

                }

            })
        }
    }

    suspend fun <T> asyncUtils(): T {
        return suspendCoroutine { continuation ->

        }
    }
}