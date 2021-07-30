package com.example.tunatest.view.appointment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tunatest.R
import com.example.tunatest.converters.convertDateToDayString
import com.example.tunatest.view.appointment.adapter.DateListAdapter.Holder
import com.example.tunatest.view.appointment.model.Date
import kotlinx.android.synthetic.main.row_date_list.view.*

class DateListAdapter(
    private val context: Context,
    private val dateList: ArrayList<Date>,
    private val listener: DateListAdapterListener
) :
    RecyclerView.Adapter<Holder>() {
    var selectedItem = -1

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView
        fun bind(data: Date, itemPosition: Int) {
            view.tvDate.text = convertDateToDayString(data.date)

            if (itemPosition == selectedItem) {
                view.tvDate.setBackgroundResource(R.drawable.bg_date_label_filled)
                view.tvDate.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                view.tvDate.setBackgroundResource(R.drawable.bg_date_label_stroked)
                view.tvDate.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            view.tvDate.setOnClickListener {
                selectedItem = itemPosition
                notifyDataSetChanged()
                listener.onDateSelected(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.row_date_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dateList[position], position)
    }

    override fun getItemCount(): Int = dateList.size

    interface DateListAdapterListener {
        fun onDateSelected(date: Date)
    }
}