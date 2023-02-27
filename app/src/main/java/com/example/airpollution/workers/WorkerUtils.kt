package com.example.airpollution.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.airpollution.BuildConfig
import com.example.airpollution.NetworkClient
import com.example.airpollution.R
import com.example.airpollution.api.model.AirPollution
import retrofit2.Response
import java.time.LocalTime

fun sendNotification(
    context: Context, content: String
) {

    val channelId = "CHANNEL_ID"
    val channelName = "NEW_NOTIFICATION"
    val notificationTitle = "미세먼지 알리미"

    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)

    manager.createNotificationChannel(
        NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
    )
    val notification = builder.setContentTitle(notificationTitle)
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setAutoCancel(true)
        .build()

    manager.notify(1, notification)
}

suspend fun getRemoteData(): List<AirPollution.Response.Body.Item>?  {

    val serviceKey = BuildConfig.apiKey

    val response = NetworkClient.apiService.getData(
        "2023",
        1,
        10,
        "json",
        serviceKey
    )

    val result: List<AirPollution.Response.Body.Item>? = try {
        Log.e("WorkerUtils", "in try")
        response.body()?.response?.body?.items
    } catch (e: NumberFormatException) {
        Log.e("WorkerUtils", e.toString())
        listOf()
    }

    return result
}
