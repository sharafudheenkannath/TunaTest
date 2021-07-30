package com.example.tunatest.view.auth.model

data class RegisterDeviceResponseDataModel(
    val location_message: String,
    val guest_user_id: Int,
    val response: String,
    val message: String,
    val device: Device
)

data class Device(
    val push_type: String,
    val location_address: String,
    val location_name: String,
    val device_id: String,
    val terminal_number: Any,
    val location_city: Any,
    val name: String,
    val id: Int,
    val type: String,
    val location_state: Any,
    val location_id: String,
    val push_token: String
)