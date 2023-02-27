package com.example.airpollution.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.airpollution.StoreDistrictName
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Worker(
    context: Context, workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    private val context = context

    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    private val todayDate: String = currentDateTime.format(dateFormatter)

    private val formatted: String = currentDateTime.format(timeFormatter)
    private val currentTime: LocalTime = LocalTime.parse(formatted, timeFormatter)

    private val oneHourBefore: LocalDateTime = currentDateTime.minus(1, ChronoUnit.HOURS)
    private val formattedOneHourBefore: String = oneHourBefore.format(timeFormatter)
    private val oneHourBeforeCurrentTime: LocalTime = LocalTime.parse(formattedOneHourBefore, timeFormatter)

    override suspend fun doWork(): Result {
        val districtName = StoreDistrictName(context).districtName.first()
        val moveName = StoreDistrictName(context).moveName.first()
        val districtNameFromUser = "$districtName$moveName"

        return try {
            getRemoteData()?.forEach { item ->
                val districtNameForContent = item.districtName
                val moveNameForContent = item.moveName
                val itemCodeForContent = if (item.itemCode == "PM25") "초미세먼지" else "미세먼지"
                val issueGbnForContent = item.issueGbn
                val issueTime = item.issueTime
                val content = "$districtNameForContent$moveNameForContent : $itemCodeForContent $issueGbnForContent $issueTime"

                if (item.issueDate == todayDate) { // check if the item's date is the same as today's date
                    val districtNameFromItem = "${item.districtName}${item.moveName}"
                    if (districtNameFromItem == districtNameFromUser) { // check if the item's district name is the same as user's
                        val issueTime = LocalTime.parse(item.issueTime, timeFormatter) // parse string to LocalTime object

                        // the job should be executed every 1 hour
                        // check if the new data was created in server in 1 hour
                        if (issueTime.isAfter(oneHourBeforeCurrentTime) || issueTime.isBefore(currentTime)) { // compare time
                            sendNotification(context, content)
                        }
                    }
                }
            }
            Result.success()
        }catch (e: Exception) {
            Log.e(TAG, e.toString())
            Result.failure()
        }
    }

    companion object{
        const val TAG = "Worker"
    }
}