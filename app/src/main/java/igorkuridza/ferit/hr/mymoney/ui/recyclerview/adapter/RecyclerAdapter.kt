package igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.recyclerview.Date
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.model.recyclerview.CategoryAndAmount
import igorkuridza.ferit.hr.mymoney.model.recyclerview.MonthYearBalance
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.holders.*
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.OnRecyclerViewItemClickListener
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.base.BaseViewHolder
import java.lang.IllegalArgumentException

class RecyclerAdapter(): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val data: MutableList<Any>
    private lateinit var listener: OnRecyclerViewItemClickListener

    companion object{
        private const val TYPE_DATE = 0
        private const val TYPE_TRANSACTION = 1
        private const val TYPE_CATEGORY_AND_AMOUNT = 2
        private const val TYPE_ACCOUNT = 3
        private const val TYPE_BALANCE = 4
        private const val TYPE_MONTH_YEAR_BALANCE = 5
    }

    init {
        data = ArrayList()
    }

    fun setListener(listener: OnRecyclerViewItemClickListener){
        this.listener = listener
    }

    fun swapData(newData: List<Any>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun getData(): List<Any> = data

    fun clearData(){
        data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val context = parent.context
        return when(viewType) {
            TYPE_DATE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.date_cell, parent, false)
                DateHolder(view)
            }
            TYPE_TRANSACTION -> {
                val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
                TransactionHolder(view)
            }
            TYPE_CATEGORY_AND_AMOUNT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
                CategoryHolder(view, listener, data)
            }
            TYPE_ACCOUNT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.account_item, parent, false)
                AccountHolder(view, listener, data)
            }
            TYPE_BALANCE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.balance_item, parent, false)
                BalanceHolder(view)
            }
            TYPE_MONTH_YEAR_BALANCE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.month_year_expensesamount_incomesamount_item, parent, false)
                MonthYearBalanceHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = data[position]
        when(holder){
            is DateHolder -> holder.bindItem(item as Date)
            is TransactionHolder -> holder.bindItem(item as Transaction)
            is CategoryHolder -> holder.bindItem(item as CategoryAndAmount)
            is AccountHolder -> holder.bindItem(item as Account)
            is BalanceHolder -> holder.bindItem(item as Float)
            is MonthYearBalanceHolder -> holder.bindItem(item as MonthYearBalance)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is Date -> TYPE_DATE
            is Transaction -> TYPE_TRANSACTION
            is CategoryAndAmount -> TYPE_CATEGORY_AND_AMOUNT
            is Account -> TYPE_ACCOUNT
            is Float -> TYPE_BALANCE
            is MonthYearBalance -> TYPE_MONTH_YEAR_BALANCE
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }
}