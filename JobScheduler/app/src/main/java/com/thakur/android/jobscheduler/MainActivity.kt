package com.thakur.android.jobscheduler

import android.app.job.JobInfo.BACKOFF_POLICY_LINEAR
import android.app.job.JobInfo.Builder
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jobScheduler = applicationContext
                .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(this,
                JobScheduleService::class.java!!)
        val jobInfoObj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Builder(777, componentName)
                // create a job that stays as scheduled even after device reboots,
                // you need call setPersisted() method by passing true as value to it
                .setPersisted(true)
                // create a job that needs to be rerun if it fails,
                // you need to use setBackOffCriteria() method passing time interval
                // for the first time retry and retry policy which is used to calculate
                // time interval for retries after first retry.
                .setBackoffCriteria(TimeUnit.MINUTES.toMillis(2000), BACKOFF_POLICY_LINEAR)
                .setPeriodic(TimeUnit.MINUTES.toMillis(2000), TimeUnit.MINUTES.toMillis(3000))
                .build() else {
            TODO("VERSION.SDK_INT < N")
        }

        jobScheduler.schedule(jobInfoObj);

        Timer().schedule(timerTask { Log.i("pending", jobScheduler.getAllPendingJobs().size.toString()) }, Date(), 5000)
    }
}
