package com.winnerwinter.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val name = inputData.getString(INPUT_DATA_KEY)
        Thread.sleep(3000)
        val sharedPreferences =
            applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        var number = sharedPreferences.getInt(name, 0)
        sharedPreferences.edit().putInt(name, ++number).apply()
        return Result.success(workDataOf(Pair(OUTPUT_DATA_KEY, "$name output")))
    }
}