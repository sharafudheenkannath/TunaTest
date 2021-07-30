package com.example.tunatest.view.auth.model

data class GuestTokenRequestDataModel(
    val secret_key: String,
    val device_id: String,
    val device_type: String,
    val location_id: String
)