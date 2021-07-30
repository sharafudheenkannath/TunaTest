package com.example.tunatest.view.appointment.model

data class CreateAppointmentRequestDataModel(
    val date_time: String,
    val device_id: String,
    val device_type: Int,
    val location_id: Int,
    val name: String,
    val phone: Long,
    val user_id: Int,
    val queue_type: Int
)