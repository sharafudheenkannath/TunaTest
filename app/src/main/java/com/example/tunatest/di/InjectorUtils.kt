package com.example.tunatest.di

import com.example.tunatest.data.repository.TunaRepo
import com.example.tunatest.view.appointment.AppointmentViewModelFactory
import com.example.tunatest.view.auth.RegisterViewModelFactory


/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    fun provideRegisterDeviceViewModelFactory(): RegisterViewModelFactory {
        return RegisterViewModelFactory(TunaRepo.getInstance())
    }

    fun provideAppointmentViewModelFactory(): AppointmentViewModelFactory {
        return AppointmentViewModelFactory(TunaRepo.getInstance())
    }

}