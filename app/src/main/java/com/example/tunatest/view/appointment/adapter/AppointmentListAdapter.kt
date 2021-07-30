package com.example.tunatest.view.appointment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tunatest.R
import com.example.tunatest.view.appointment.model.Date
import com.example.tunatest.view.appointment.model.DateDataModel
import kotlinx.android.synthetic.main.row_appointment_list.view.*

class AppointmentListAdapter(
    private val context: Context,
    private val dateList: ArrayList<DateDataModel>, private val listener: AppointmentListInterface
) :
    RecyclerView.Adapter<AppointmentListAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),
        DateListAdapter.DateListAdapterListener {

        private val view = itemView
        private lateinit var dateAdapter: DateListAdapter
        private var selectedDate: Date? = null
        private var selectedTime: String? = null
        private var slotSelectedPosition = -1

        fun bind(data: DateDataModel, itemPosition: Int) {

            view.tvDate.text = data.dateString
            val list = data.dateList
            dateAdapter = DateListAdapter(context, list, this)
            view.rvDateList.adapter = dateAdapter

            view.actvSelectTimeSlot.setOnItemClickListener { adapterView, v, position, id ->
                updateSelectTimeSpinner(position)

                view.groupSelectTime.visibility = View.VISIBLE
                slotSelectedPosition = position
            }

            view.actvSelectTime.setOnItemClickListener { adapterView, v, position, id ->
                view.btnSchedule.visibility = View.VISIBLE
                selectedTime =
                    selectedDate?.slot?.get(slotSelectedPosition)?.value?.get(position)?.datetime
            }

            view.btnSchedule.setOnClickListener {
                when {
                    dateAdapter.selectedItem == -1 -> {
                        Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
                    }
                    slotSelectedPosition == -1 -> {
                        Toast.makeText(context, "Please select time slot", Toast.LENGTH_SHORT)
                            .show()
                    }
                    selectedTime == null -> {
                        Toast.makeText(context, "Please select time ", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        listener.onScheduleClicked(selectedTime!!)
                    }
                }
            }

        }

        // update dat to the spinner of Slot selection
        private fun updateTimeSlotSpinner() {
            val adapterTimeSlot = ArrayAdapter(
                context, android.R.layout.simple_spinner_item,
                selectedDate?.slot?.map { it.name }!!
            )
            view.actvSelectTimeSlot.setAdapter(adapterTimeSlot)
            view.actvSelectTimeSlot.text.clear()
            view.actvSelectTime.text.clear()
            slotSelectedPosition = -1
        }

        // update data to the spinner of time selection
        private fun updateSelectTimeSpinner(position: Int) {
            val adapterTime = ArrayAdapter(
                context, android.R.layout.simple_spinner_item,
                selectedDate?.slot?.get(position)?.value?.map { it.time }!!
            )
            view.actvSelectTime.setAdapter(adapterTime)
            view.actvSelectTime.text.clear()
            selectedTime = null
        }

        override fun onDateSelected(date: Date) {
            view.groupChooseTimeSlot.visibility = View.VISIBLE
            selectedDate = date
            updateTimeSlotSpinner()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.row_appointment_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dateList[position], position)
    }

    override fun getItemCount(): Int = dateList.size

    interface AppointmentListInterface {
        fun onScheduleClicked(dateTime: String)
    }
}