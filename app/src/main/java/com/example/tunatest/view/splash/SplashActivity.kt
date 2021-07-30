package com.example.tunatest.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tunatest.R
import com.example.tunatest.base.BaseActivity
import com.example.tunatest.data.preference.SharedPref
import com.example.tunatest.view.appointment.AppointmentActivity
import com.example.tunatest.view.auth.RegisterDeviceActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (SharedPref.isRegistered) {
                AppointmentActivity.start(this)
            } else {
                RegisterDeviceActivity.start(this)
            }
        }, 1000)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun setupUI() {

    }

    override fun setupObservers() {

    }

    override fun setupArguments() {

    }
}