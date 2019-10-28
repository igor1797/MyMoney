package igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders

import android.view.View
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setColor
import igorkuridza.ferit.hr.mymoney.model.recyclerview.CategoryAndAmount
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.OnRecyclerViewItemClickListener
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import kotlinx.android.synthetic.main.category_item.*

class CategoryHolder(view: View, private val listener: OnRecyclerViewItemClickListener, private val data: List<Any>): BaseViewHolder<CategoryAndAmount>(view), View.OnClickListener{

    override fun bindItem(item: CategoryAndAmount){
        tvCategoryName.text = item.category.name
        tvAmount.text = joinStrings(item.amount.toString()," kn")
        ivCategoryColor.setColor(item.category.color.getColor())

        containerView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val position = adapterPosition
        if(position < 0)
            return
        val categoryAndAmount = data[position] as CategoryAndAmount
        listener.onItemClick(categoryAndAmount.category)
    }
}