package com.example.tunatest.view.appointment

import androidx.lifecycle.ViewModel
import com.example.tunatest.data.repository.TunaRepo

class AppointmentViewModel(private val repo: TunaRepo) : ViewModel() {

    val dateListResult = repo.dateListResult
    val createAppointmentResult = repo.createAppointmentResult

    fun getDateList() {
        repo.getDateList()
    }

    fun createAppointment(dateTime: String) {
        repo.createAppointment(dateTime)
    }
}