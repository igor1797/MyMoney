package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter.RecyclerAdapter
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transactions.*
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectFragmentDialog4
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter.TransactionsFragmentPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TransactionsFragment : BaseFragment(), SelectListener, TransactionsFragmentView {

    private val transactionAdapter by lazy { RecyclerAdapter() }
    private val presenter: TransactionsFragmentPresenter by inject {(parametersOf(this))}
    private var dateType: String = TYPE_TODAY

    override fun getLayoutResourceId() = R.layout.fragment_transactions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setupUi() {
        tvDate.text = getTodayDate()
        setRecyclerView()
        presenter.getTransactionAdapterData(TYPE_TODAY)
        ivShowSelect.setOnClickListener { showSelectDialog() }
        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                val data = transactionAdapter.getData()
                presenter.filterDataByNote(dateType, searchText)
                return false
            }
        })
    }

    private fun setRecyclerView(){
        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context)
            setDivider()
        }
    }

    private fun checkData(newData: List<Any>){
        if(newData.isNotEmpty()){
            swapAdapterData(newData)
            noTransactions.gone()
        }
        else{
            transactionAdapter.clearData()
            noTransactions.visible()
        }
    }

    override fun onGetTransactionAdapterData(newData: List<Any>) {
        checkData(newData)
    }

    private fun showSelectDialog(){
        val fragmentManager = fragmentManager
        val selectDialog = SelectFragmentDialog4.newInstance()
        selectDialog.show(fragmentManager!!, "")
        selectDialog.setSelectListener(this)
    }

    private fun swapAdapterData(newData: List<Any>){
        transactionAdapter.swapData(newData)
    }

    override fun onSelect(type: String) {
        presenter.getTransactionAdapterData(type)
        when(type){
            TYPE_ALL_TIME -> {
                dateType = type
                tvDate.text = getText(R.string.cijelo_vrijeme)
            }
            TYPE_TODAY -> {
                dateType = type
                tvDate.text = getTodayDate()
            }
            TYPE_CURRENT_MONTH -> {
                dateType = type
                tvDate.text = getCurrentMonth()
            }
            TYPE_CURRENT_YEAR -> {
                dateType = type
                tvDate.text = getCurrentYear()
            }
        }
    }

    companion object{
        fun newInstance(): TransactionsFragment =
            TransactionsFragment()
    }
}
