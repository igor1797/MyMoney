package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders

import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setTextViewTextColor
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.balance_item.*

class BalanceHolder(view: View): BaseViewHolder<Float>(view){

    override fun bindItem(item: Float) {
        tvBalanceAmount.text = joinStrings(item.toString()," kn")
        when{
            item == 0F -> tvBalanceAmount.setTextViewTextColor(R.color.colorBlack)
            item < 0F -> tvBalanceAmount.setTextViewTextColor(R.color.red)
            else -> tvBalanceAmount.setTextViewTextColor(R.color.green)
        }
    }
}