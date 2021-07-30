package com.example.tunatest.view.auth

import androidx.lifecycle.ViewModel
import com.example.tunatest.data.repository.TunaRepo

class RegisterDeviceViewModel(private val repo: TunaRepo) : ViewModel() {

    val guestTokenResult = repo.guestTokenResult
    val registerDeviceResult = repo.registerDeviceResult

    fun getGuestToken(deviceId: String) {
        repo.getGuestToken(deviceId)
    }

    fun registerDevice(deviceId: String) {
        repo.registerDevice(deviceId)
    }
}