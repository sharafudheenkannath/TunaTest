package com.example.tunatest.view.appointment.model

data class CreateAppointmentResponseDataModel(
    val address: String,
    val appointment: Appointment,
    val appointment_datetime: String,
    val appointment_id: Int,
    val is_primary: Any,
    val message: String,
    val name: String,
    val response: String
)

data class Appointment(
    val appointment_date: String,
    val appointment_time: String,
    val created_at: String,
    val datetime: String,
    val device_type: String,
    val email: Any,
    val id: Int,
    val is_out: Int,
    val location_id: String,
    val name: String,
    val phone: String,
    val queue_type: String,
    val updated_at: String,
    val user_id: Int
)