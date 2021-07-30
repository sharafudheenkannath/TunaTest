package com.example.tunatest.app

import android.app.Application
import android.content.IntentFilter
import android.util.Log
import com.example.tunatest.data.preference.SharedPref

class TunaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPref.init(this)

    }
}