package com.example.tunatest.view.appointment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import com.example.tunatest.R
import com.example.tunatest.base.BaseActivity
import com.example.tunatest.converters.convertDateToString
import com.example.tunatest.data.constants.TunaConstants
import com.example.tunatest.data.networkmodel.NetworkResult
import com.example.tunatest.di.InjectorUtils
import com.example.tunatest.view.appointment.adapter.AppointmentListAdapter
import com.example.tunatest.view.appointment.model.CreateAppointmentResponseDataModel
import com.example.tunatest.view.appointment.model.Date
import com.example.tunatest.view.appointment.model.DateDataModel
import com.example.tunatest.view.loader.LottieDialogFragment
import kotlinx.android.synthetic.main.activity_appointment.*
import kotlinx.android.synthetic.main.dialog_appointment_success.*

class AppointmentActivity : BaseActivity(), AppointmentListAdapter.AppointmentListInterface {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AppointmentActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val dateList = ArrayList<DateDataModel>()
    private lateinit var appointmentListAdapter: AppointmentListAdapter
    private lateinit var loaderDialog: LottieDialogFragment
    private val viewModel: AppointmentViewModel by viewModels {
        InjectorUtils.provideAppointmentViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loaderDialog = LottieDialogFragment.newInstance()
        setupObservers()

        viewModel.getDateList()
    }

    override fun getLayoutId(): Int = R.layout.activity_appointment

    override fun setupUI() {

        appointmentListAdapter = AppointmentListAdapter(this, dateList, this)
        rvAppointmentList.adapter = appointmentListAdapter
    }

    override fun setupObservers() {

        viewModel.dateListResult.observe(this, {
            when (it.status) {
                NetworkResult.Status.LOADING -> {
                    loaderDialog.show(supportFragmentManager, "")
                }
                NetworkResult.Status.SUCCESS -> {
                    loaderDialog.dismiss()
                    if (it.data == null) {
                        showToast("Null data found")
                    } else {
                        if (it.data.response == TunaConstants.API_RESPONSE_SUCCESS) {
                            groupDateList(it.data.date)
                        } else {
                            showToast("Failed to load date list")
                        }
                    }
                }
                NetworkResult.Status.ERROR -> {
                    loaderDialog.dismiss()
                    showToast(it.message!!)
                }

            }
        })

        viewModel.createAppointmentResult.observe(this, {
            when (it.status) {
                NetworkResult.Status.LOADING -> {
                    loaderDialog.show(supportFragmentManager, "")
                }
                NetworkResult.Status.SUCCESS -> {
                    loaderDialog.dismiss()
                    if (it.data == null) {
                        showToast("Null data found")
                    } else {
                        if (it.data.response == TunaConstants.API_RESPONSE_SUCCESS) {
                            showSuccessDialog(it.data)
                        } else {
                            showToast("Failed to load date list")
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

    private fun groupDateList(date: List<Date>) {
        dateList.clear()
        for (i in 1..12) {
            val month: String = "0$i".takeLast(2)
            val result = date.filter { item -> item.date.substring(0, 2) == month }
            if (result.isNotEmpty()) {
                val date = convertDateToString(result[0].date)
                dateList.add(DateDataModel(date, result as ArrayList<Date>))
            }
        }
        appointmentListAdapter.notifyDataSetChanged()
    }

    override fun onScheduleClicked(dateTime: String) {
        viewModel.createAppointment(dateTime)
    }

    private fun showSuccessDialog(data: CreateAppointmentResponseDataModel) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_appointment_success)
        dialog.show()

        dialog.tvDateAppointmentSuccess.text =
            "Date : ${data.appointment_datetime.substring(0, 10)}"
        dialog.tvTimeAppointmentSuccess.text =
            "Time : ${data.appointment_datetime.substring(11)}"
        dialog.tvLocationAppointmentSuccess.text = "Location : ${data.address}"
    }

}