package com.example.airpollution

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.airpollution.workers.Worker
import java.util.concurrent.TimeUnit

class Application() : Application() {

    override fun onCreate() {
        super.onCreate()

        val workManager = WorkManager.getInstance(applicationContext)

        // check network connection
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        // run job every 1 hour (15 minutes for test)
        val requestConstraint  = PeriodicWorkRequestBuilder<Worker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(requestConstraint)
    }
}