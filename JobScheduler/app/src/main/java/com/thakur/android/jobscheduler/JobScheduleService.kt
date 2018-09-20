package com.thakur.android.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.RemoteException
import android.util.Log
import java.util.*
import kotlin.concurrent.timerTask

class JobScheduleService:JobService(){

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("1", "onStartJob")
        sendMessage(1, params = params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("1", "onStopJob")
        sendMessage(2, params = params)
        return true
    }

    private fun sendMessage(messageID: Int, params: JobParameters?) {
        // If this service is launched by the JobScheduler, there's no callback Messenger. It
        // only exists when the MainActivity calls startService() with the callback in the Intent.
        try {
            Timer().schedule(timerTask {
                Log.e(messageID.toString(), "job finished")
                jobFinished(params, true)
            }, 2000)
            Log.i(messageID.toString(), "sending message on schedule")

        } catch (e: RemoteException) {
            Log.e(messageID.toString(), "Error passing service object back to activity.")
        }
        finally {


        }

    }
}