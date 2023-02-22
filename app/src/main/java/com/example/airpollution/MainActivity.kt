package com.example.airpollution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val SERVICE_KEY=BuildConfig.apiKey
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            getLogs()
        }
    }

    private suspend fun getLogs() {
        try {
            val response = NetworkClient.apiService.getData(
                "2022",
                1,
                10,
                "json",
                SERVICE_KEY,
                "PM25"
            )
            if (response.isSuccessful) {
                response.body().let {
                    it?.response?.body?.items?.get(1)?.let { it1 -> Log.e("try", it1.districtName) }
                }
            }else {
                Log.e("Result", response.message())
            }
        }catch (e: Exception) {
            Log.e("MainActivity", e.toString())
        }
    }
}