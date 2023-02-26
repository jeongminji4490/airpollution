package com.example.airpollution

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Worker(
    context: Context, workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    private val context = context

    private val serviceKey = BuildConfig.apiKey

    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    private val todayDate: String = currentDateTime.format(dateFormatter)

    private val formatted: String = currentDateTime.format(timeFormatter)
    private val currentTime: LocalTime = LocalTime.parse(formatted, timeFormatter)

    private val oneHourBefore: LocalDateTime = currentDateTime.minus(1, ChronoUnit.HOURS)
    private val formattedOneHourBefore: String = oneHourBefore.format(timeFormatter)
    private val oneHourBeforeCurrentTime: LocalTime = LocalTime.parse(formattedOneHourBefore, timeFormatter)

    private val channelId = "CHANNEL_ID"
    private val channelName = "NEW_NOTIFICATION"
    private val notificationTitle = "미세먼지 알리미"

    private var manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)

    override suspend fun doWork(): Result {
        val districtName = StoreDistrictName(context).districtName.first()
        val moveName = StoreDistrictName(context).moveName.first()
        val districtNameFromUser = "$districtName$moveName"

        return try {
            val response = NetworkClient.apiService.getData(
                "2023",
                1,
                10,
                "json",
                serviceKey
            )
            if (response.isSuccessful) {

                response.body()?.let { airPollution ->
                    airPollution.response.body.items.forEach { item ->
                        val districtNameForContent = item.districtName
                        val moveNameForContent = item.moveName
                        val itemCodeForContent = if (item.itemCode == "PM25") "초미세먼지" else "미세먼지"
                        val issueGbnForContent = item.issueGbn
                        val issueTime = item.issueTime
                        val test = "$districtNameForContent$moveNameForContent : $itemCodeForContent $issueGbnForContent $issueTime"
                        Log.e("Worker", test)
                        if (item.issueDate == todayDate) { // check if the item's date is the same as today's date
                            val districtNameFromItem = "${item.districtName}${item.moveName}"
                            if (districtNameFromItem == districtNameFromUser) { // check if the item's district name is the same as user's
                                val issueTime = LocalTime.parse(item.issueTime, timeFormatter) // parse string to LocalTime object

                                // the job should be executed every 1 hour
                                // check if the new data was created in server in 1 hour
                                if (issueTime.isAfter(oneHourBeforeCurrentTime) || issueTime.isBefore(currentTime)) { // compare time
                                    manager.createNotificationChannel(
                                        NotificationChannel(
                                            channelId,
                                            channelName,
                                            NotificationManager.IMPORTANCE_DEFAULT
                                        )
                                    )
                                    val content = "$item.districtName${item.moveName} : ${item.itemCode} ${item.issueGbn}"
                                    val notification = builder.setContentTitle(notificationTitle)
                                        .setContentText(content)
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setAutoCancel(true)
                                        .build()

                                    manager.notify(1, notification)
                                }
                            }
                        }
                    }
                }
            }
            Result.success()
        }catch (e: Exception) {
            Log.e(TAG, "Network Error")
            Result.failure()
        }
    }

    companion object{
        const val TAG = "Worker"
    }
}