package com.example.tunatest.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tunatest.data.repository.TunaRepo

class RegisterViewModelFactory(private val repo: TunaRepo) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterDeviceViewModel(repo) as T
    }
}