package com.example.myretrfit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var getAppDataBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppDataBtn = findViewById(R.id.getAppDataBtn)
        getAppDataBtn?.setOnClickListener(View.OnClickListener {
            val retrfit = Retrofit.Builder()
                .baseUrl("http://172.0.0.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrfit.create(AppService::class.java)
            appService.getAppData().enqueue(object:Callback<List<App>>{
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    if (list!=null){
                        for (app in list){
                            Log.e("TAG", "id: ${app.id}" )
                            Log.e("TAG", "name: ${app.name}" )
                            Log.e("TAG", "version: ${app.version}" )
                        }
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        })
        var create = ServerCreate.create(AppService::class.java).getAppData()

    }
}