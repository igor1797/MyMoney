package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders


import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setColor
import igorkuridza.ferit.hr.mymoney.common.setTextViewTextColor
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.category_item.ivCategoryColor
import kotlinx.android.synthetic.main.category_item.tvAmount
import kotlinx.android.synthetic.main.category_item.tvCategoryName
import kotlinx.android.synthetic.main.transaction_item.*

class TransactionHolder(view: View): BaseViewHolder<Transaction>(view){

    override fun bindItem(item: Transaction){

        ivCategoryColor.setColor(item.category.color.getColor())
        tvCategoryName.text = item.category.name
        if(item.category.typeOfCategory == TypeOfCategory.EXPENSES) {
            tvAmount.text = joinStrings("-", item.amountOfMoney.toString())
            tvAmount.setTextViewTextColor(R.color.red)
        }
        else {
            tvAmount.text = joinStrings("+", item.amountOfMoney.toString())
            tvAmount.setTextViewTextColor(R.color.green)

        }
        tvAccountName.text = item.account.name
        tvNote.text = item.note
    }
}