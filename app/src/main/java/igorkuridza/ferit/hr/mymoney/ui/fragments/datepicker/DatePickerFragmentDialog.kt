package igorkuridza.ferit.hr.mymoney.ui.fragments.datepicker

import android.os.Bundle
import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.convertMilliSecondsToDate
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragmentDialog
import kotlinx.android.synthetic.main.fragment_with_calendar.*
import java.util.*

class DatePickerFragmentDialog: BaseFragmentDialog() {

    private var datePickerListener: DatePickerListener? = null
    private var calendarInstance: Calendar? = null

    override fun getLayoutResourceId(): Int = R.layout.fragment_with_calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPickDate.setOnClickListener {
            datePickerListener?.onDatePicked(onPickDateClick())
            dismiss()
        }

        if(calendarInstance == null) calendarInstance = Calendar.getInstance()

        calendar.setOnDateChangeListener{
                _, year, month, day -> calendarInstance?.set(year, month,day)
        }
    }

    fun setDatePickerListener(datePickerListener: DatePickerListener){
        this.datePickerListener = datePickerListener
    }

    private fun onPickDateClick(): String{
        return convertMilliSecondsToDate(calendarInstance!!.timeInMillis)
    }

    companion object{
        fun newInstance(): DatePickerFragmentDialog =
            DatePickerFragmentDialog()
    }
}