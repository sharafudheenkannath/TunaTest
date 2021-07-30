package com.example.tunatest.view.auth.model

data class RegisterDeviceRequestDataModel(
    val push_type: Int,
    val device_id: String,
    val name: String,
    val device_type: Int,
    val location_id: Int,
    val push_token: String
)