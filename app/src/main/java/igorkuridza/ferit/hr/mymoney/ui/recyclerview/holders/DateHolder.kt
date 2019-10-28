package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders

import android.view.View
import igorkuridza.ferit.hr.mymoney.model.recyclerview.Date
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.date_cell.*

class DateHolder(view: View): BaseViewHolder<Date>(view){

    override fun bindItem(item: Date){
        tvDay.text = item.day.toString()
        tvMonth.text = item.month
        tvYear.text = item.year.toString()
    }
}