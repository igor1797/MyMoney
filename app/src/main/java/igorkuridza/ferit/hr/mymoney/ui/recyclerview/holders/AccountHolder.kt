package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders


import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setColor
import igorkuridza.ferit.hr.mymoney.common.setTextViewTextColor
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.account_item.*

class AccountHolder(view: View, private val listener: OnRecyclerViewItemClickListener, private val data: List<Any>): BaseViewHolder<Account>(view), View.OnClickListener{

    override fun bindItem(item: Account){

        ivAccountImage.setColor(item.color)
        tvAccountName.text = item.name
        tvAccountAmount.text = joinStrings(item.amountOfMoney.toString() ," kn")
        when {
            item.amountOfMoney < 0 -> tvAccountAmount.setTextViewTextColor(R.color.red)
            item.amountOfMoney > 0 -> tvAccountAmount.setTextViewTextColor(R.color.green)
            else -> tvAccountAmount.setTextViewTextColor(R.color.colorBlackTrans)
        }

        containerView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val position = adapterPosition
        if(position < 0)
            return
        val account = data[position] as Account
        listener.onItemClick(account)
    }
}