package com.example.tunatest.view.appointment.model

data class DateListResponseDataModel(
    val date: List<Date>,
    val response: String
)

data class Date(
    val availability: Int,
    val date: String,
    val slot: List<Slot>
)

data class Slot(
    val name: String,
    val slot_availability: Int,
    val value: List<Value>
)

data class Value(
    val available: Int,
    val datetime: String,
    val hour: String,
    val time: String
)