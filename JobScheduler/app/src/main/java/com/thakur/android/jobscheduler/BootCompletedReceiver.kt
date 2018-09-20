package com.thakur.android.jobscheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("1", "onReceive");

//        context?.let { connectivityCheckJobSchedule(it) }
    }
}