package com.example.tunatest.view.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import com.example.tunatest.R
import com.example.tunatest.base.BaseActivity
import com.example.tunatest.data.constants.TunaConstants.API_RESPONSE_SUCCESS
import com.example.tunatest.data.networkmodel.NetworkResult
import com.example.tunatest.data.preference.SharedPref
import com.example.tunatest.di.InjectorUtils
import com.example.tunatest.view.appointment.AppointmentActivity
import com.example.tunatest.view.loader.LottieDialogFragment
import kotlinx.android.synthetic.main.activity_register_device.*
import timber.log.Timber

class RegisterDeviceActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterDeviceActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var loaderDialog: LottieDialogFragment
    private var deviceId: String? = null
    private val viewModel: RegisterDeviceViewModel by viewModels {
        InjectorUtils.provideRegisterDeviceViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDeviceId()

        loaderDialog = LottieDialogFragment.newInstance()
        setupObservers()


    }

    private fun getDeviceId() {
        deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID)
        Timber.d("Device ID -> $deviceId")

    }

    override fun getLayoutId(): Int = R.layout.activity_register_device

    override fun setupUI() {

        btnRegisterDevice.setOnClickListener {
            if (deviceId == null) {
                showToast("Couldn't find the device ID")
            } else {
                viewModel.getGuestToken(deviceId!!)
            }
        }
    }

    override fun setupObservers() {
        viewModel.guestTokenResult.observe(this, {
            when (it.status) {
                NetworkResult.Status.LOADING -> {
                    loaderDialog.show(supportFragmentManager, "")
                }
                NetworkResult.Status.SUCCESS -> {
                    if (it.data == null) {
                        showToast("Null data found")
                    } else {
                        if (it.data.response == API_RESPONSE_SUCCESS) {
                            SharedPref.bearerToken = it.data.token
                            if (deviceId == null) {
                                showToast("Couldn't find the device ID")
                                loaderDialog.dismiss()
                            } else {
                                viewModel.registerDevice(deviceId!!)
                            }
                        } else {
                            showToast("Failed to get guest token")
                        }
                    }
                }
                NetworkResult.Status.ERROR -> {
                    loaderDialog.dismiss()
                    showToast(it.message!!)
                }

            }
        })
        viewModel.registerDeviceResult.observe(this, {
            when (it.status) {
                NetworkResult.Status.LOADING -> {
                }
                NetworkResult.Status.SUCCESS -> {
                    loaderDialog.dismiss()
                    if (it.data == null) {
                        showToast("Null data found")
                    } else {
                        if (it.data.response == API_RESPONSE_SUCCESS) {
                            SharedPref.deviceId = deviceId!!
                            SharedPref.isRegistered = true
                            SharedPref.userId = it.data.guest_user_id.toString()
                            AppointmentActivity.start(this)
                        } else {
                            showToast(it.data.message)
                        }
                    }
                }
                NetworkResult.Status.ERROR -> {
                    loaderDialog.dismiss()
                    showToast(it.message!!)
                }

            }
        })
    }

    override fun setupArguments() {
    }
}