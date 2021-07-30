package com.example.tunatest.view.appointment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tunatest.data.repository.TunaRepo
import com.example.tunatest.view.auth.RegisterDeviceViewModel

class AppointmentViewModelFactory(private val repo: TunaRepo) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppointmentViewModel(repo) as T
    }
}