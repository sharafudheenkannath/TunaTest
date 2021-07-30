package com.example.tunatest.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.tunatest.BuildConfig
import com.example.tunatest.data.api.ApiClient
import com.example.tunatest.data.networkmodel.NetworkResult
import com.example.tunatest.data.preference.SharedPref
import com.example.tunatest.view.appointment.model.CreateAppointmentRequestDataModel
import com.example.tunatest.view.appointment.model.CreateAppointmentResponseDataModel
import com.example.tunatest.view.appointment.model.DateListRequestDataModel
import com.example.tunatest.view.appointment.model.DateListResponseDataModel
import com.example.tunatest.view.auth.model.GuestTokenRequestDataModel
import com.example.tunatest.view.auth.model.GuestTokenResponseDataModel
import com.example.tunatest.view.auth.model.RegisterDeviceRequestDataModel
import com.example.tunatest.view.auth.model.RegisterDeviceResponseDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TunaRepo {
    companion object {

        fun getInstance() = TunaRepo()
    }

    val guestTokenResult = MutableLiveData<NetworkResult<GuestTokenResponseDataModel>>()
    val registerDeviceResult = MutableLiveData<NetworkResult<RegisterDeviceResponseDataModel>>()
    val dateListResult = MutableLiveData<NetworkResult<DateListResponseDataModel>>()
    val createAppointmentResult =
        MutableLiveData<NetworkResult<CreateAppointmentResponseDataModel>>()

    fun getGuestToken(deviceId: String) {

        guestTokenResult.postValue(NetworkResult.loading())
        val reqData = GuestTokenRequestDataModel(BuildConfig.SECRET_KEY, deviceId, "3", "1")
        ApiClient.instance?.apiService?.guestTokenAccess(reqData)
            ?.enqueue(object : Callback<GuestTokenResponseDataModel> {
                override fun onResponse(
                    call: Call<GuestTokenResponseDataModel>,
                    response: Response<GuestTokenResponseDataModel>
                ) {
                    if (response.body() == null) {
                        guestTokenResult.postValue(NetworkResult.error("Null data found"))
                    } else {
                        guestTokenResult.postValue(NetworkResult.success(response.body()!!))
                    }
                }

                override fun onFailure(call: Call<GuestTokenResponseDataModel>, t: Throwable) {
                    Timber.d("getGuestToken Api error -> ${t.message.toString()}")
                    guestTokenResult.postValue(NetworkResult.error(t.message.toString()))
                }

            })
    }

    fun registerDevice(deviceId: String) {

        val bearerToken = "Bearer ${SharedPref.bearerToken}"
        registerDeviceResult.postValue(NetworkResult.loading())
        val reqData = RegisterDeviceRequestDataModel(2, deviceId, "OPPO A53", 3, 1, "d32adfAsfCds")
        ApiClient.instance?.apiService?.registerDevice(bearerToken, reqData)
            ?.enqueue(object : Callback<RegisterDeviceResponseDataModel> {
                override fun onResponse(
                    call: Call<RegisterDeviceResponseDataModel>,
                    response: Response<RegisterDeviceResponseDataModel>
                ) {
                    if (response.body() == null) {
                        registerDeviceResult.postValue(NetworkResult.error("Null data found"))
                    } else {
                        registerDeviceResult.postValue(NetworkResult.success(response.body()!!))
                    }
                }

                override fun onFailure(call: Call<RegisterDeviceResponseDataModel>, t: Throwable) {
                    Timber.d("registerDevice Api error -> ${t.message.toString()}")
                    registerDeviceResult.postValue(NetworkResult.error(t.message.toString()))
                }

            })
    }

    fun getDateList() {

        val bearerToken = "Bearer ${SharedPref.bearerToken}"
        dateListResult.postValue(NetworkResult.loading())
        val reqData = DateListRequestDataModel(SharedPref.deviceId, 3, 1)
        ApiClient.instance?.apiService?.getDateList(bearerToken, reqData)
            ?.enqueue(object : Callback<DateListResponseDataModel> {
                override fun onResponse(
                    call: Call<DateListResponseDataModel>,
                    response: Response<DateListResponseDataModel>
                ) {
                    if (response.body() == null) {
                        dateListResult.postValue(NetworkResult.error("Null data found"))
                    } else {
                        dateListResult.postValue(NetworkResult.success(response.body()!!))
                    }
                }

                override fun onFailure(call: Call<DateListResponseDataModel>, t: Throwable) {
                    Timber.d("getDateList Api error -> ${t.message.toString()}")
                    dateListResult.postValue(NetworkResult.error(t.message.toString()))
                }

            })
    }

    fun createAppointment(dateTime: String) {

        val bearerToken = "Bearer ${SharedPref.bearerToken}"
        dateListResult.postValue(NetworkResult.loading())
        val reqData = CreateAppointmentRequestDataModel(
            dateTime,
            SharedPref.deviceId,
            3,
            1,
            "Tuna",
            7777777777,
            SharedPref.userId.toInt(),
            1
        )
        ApiClient.instance?.apiService?.createAppointment(bearerToken, reqData)
            ?.enqueue(object : Callback<CreateAppointmentResponseDataModel> {
                override fun onResponse(
                    call: Call<CreateAppointmentResponseDataModel>,
                    response: Response<CreateAppointmentResponseDataModel>
                ) {
                    if (response.body() == null) {
                        createAppointmentResult.postValue(NetworkResult.error("Null data found"))
                    } else {
                        createAppointmentResult.postValue(NetworkResult.success(response.body()!!))
                    }
                }

                override fun onFailure(
                    call: Call<CreateAppointmentResponseDataModel>,
                    t: Throwable
                ) {
                    Timber.d("getDateList Api error -> ${t.message.toString()}")
                    createAppointmentResult.postValue(NetworkResult.error(t.message.toString()))
                }

            })
    }
}