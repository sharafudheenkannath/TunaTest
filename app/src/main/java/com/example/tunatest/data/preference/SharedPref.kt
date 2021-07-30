package com.example.tunatest.data.preference

import android.content.Context
import android.content.SharedPreferences

object SharedPref {

    private const val IS_REGISTERED = "is_registered"
    private const val BEARER_TOKEN = "bearer_token"
    private const val USER_ID = "user_id"
    private const val DEVICE_ID = "device_id"
    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("tuna_preference", Context.MODE_PRIVATE)
    }

    var isRegistered: Boolean
        get() = sharedPref.getBoolean(IS_REGISTERED, false)
        set(value) = sharedPref.edit().putBoolean(IS_REGISTERED, value).apply()

    var bearerToken: String
        get() = sharedPref.getString(BEARER_TOKEN, "").toString()
        set(value) = sharedPref.edit().putString(BEARER_TOKEN, value).apply()

    var userId: String
        get() = sharedPref.getString(USER_ID, "").toString()
        set(value) = sharedPref.edit().putString(USER_ID, value).apply()

    var deviceId: String
        get() = sharedPref.getString(DEVICE_ID, "").toString()
        set(value) = sharedPref.edit().putString(DEVICE_ID, value).apply()

}