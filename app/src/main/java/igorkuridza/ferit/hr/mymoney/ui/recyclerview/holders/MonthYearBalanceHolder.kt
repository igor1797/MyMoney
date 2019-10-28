package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders

import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setTextViewTextColor
import igorkuridza.ferit.hr.mymoney.model.recyclerview.MonthYearBalance
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.month_year_expensesamount_incomesamount_item.*

class MonthYearBalanceHolder(view: View): BaseViewHolder<MonthYearBalance>(view){

    override fun bindItem(item: MonthYearBalance) {
        tvMonth.text = item.month
        tvYear.text = item.year.toString()

        val expensesTotalAmount = item.expensesTotalAmount
        tvExpensesTotalAmount.text = joinStrings(expensesTotalAmount.toString()," kn")
        when(expensesTotalAmount){
            0F -> tvExpensesTotalAmount.setTextViewTextColor(R.color.colorWhite)
            else -> tvExpensesTotalAmount.setTextViewTextColor(R.color.red)
        }

        val incomesTotalAmount = item.incomesTotalAmount
        tvIncomesTotalAmount.text = joinStrings(incomesTotalAmount.toString()," kn")
        when(incomesTotalAmount){
            0F -> tvIncomesTotalAmount.setTextViewTextColor(R.color.colorWhite)
            else -> tvIncomesTotalAmount.setTextViewTextColor(R.color.lightGreen)
        }
    }
}