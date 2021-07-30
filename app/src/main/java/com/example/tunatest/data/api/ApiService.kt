package com.example.tunatest.data.api

import com.example.tunatest.view.appointment.model.CreateAppointmentRequestDataModel
import com.example.tunatest.view.appointment.model.CreateAppointmentResponseDataModel
import com.example.tunatest.view.appointment.model.DateListRequestDataModel
import com.example.tunatest.view.appointment.model.DateListResponseDataModel
import com.example.tunatest.view.auth.model.GuestTokenRequestDataModel
import com.example.tunatest.view.auth.model.GuestTokenResponseDataModel
import com.example.tunatest.view.auth.model.RegisterDeviceRequestDataModel
import com.example.tunatest.view.auth.model.RegisterDeviceResponseDataModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {

    @POST("/api/guestToken")
    fun guestTokenAccess(@Body request: GuestTokenRequestDataModel): Call<GuestTokenResponseDataModel>

    @POST("/api/deviceRegister")
    fun registerDevice(
        @Header("Authorization")
        authHeader: String,
        @Body request: RegisterDeviceRequestDataModel
    ): Call<RegisterDeviceResponseDataModel>

    @POST("/api/appointmentDateList")
    fun getDateList(
        @Header("Authorization")
        authHeader: String,
        @Body request: DateListRequestDataModel
    ): Call<DateListResponseDataModel>

    @POST("/api/createAppointment")
    fun createAppointment(
        @Header("Authorization")
        authHeader: String,
        @Body request: CreateAppointmentRequestDataModel
    ): Call<CreateAppointmentResponseDataModel>
}
