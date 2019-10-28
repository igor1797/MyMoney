package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter.RecyclerAdapter
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transactions.*
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.ui.fragments.search.SearchFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.search.SearchListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectFragmentDialog4
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter.TransactionsFragmentPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TransactionsFragment : BaseFragment(), SelectListener, SearchListener, TransactionsFragmentView {

    private val transactionAdapter by lazy { RecyclerAdapter() }
    private val presenter: TransactionsFragmentPresenter by inject {(parametersOf(this))}

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
        search.setOnClickListener { showSearchDialog() }
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

    private fun showSearchDialog(){
        val fragmentManager = fragmentManager
        val selectDialog = SearchFragmentDialog.newInstance()
        selectDialog.show(fragmentManager!!, "")
        selectDialog.setSearchListener(this)
    }

    private fun swapAdapterData(newData: List<Any>){
        transactionAdapter.swapData(newData)
    }

    override fun onSelect(type: String) {
        presenter.getTransactionAdapterData(type)
        when(type){
            TYPE_ALL_TIME -> tvDate.text = getText(R.string.cijelo_vrijeme)
            TYPE_TODAY -> tvDate.text = getTodayDate()
            TYPE_CURRENT_MONTH -> tvDate.text = getCurrentMonth()
            TYPE_CURRENT_YEAR -> tvDate.text = getCurrentYear()
        }
    }

    override fun onGetTransactionsByNote(transactions: List<Any>){
        checkData(transactions)
    }

    override fun onNoteSearched(note: String) {
        presenter.getTransactionByNote(note)
    }

    companion object{
        fun newInstance(): TransactionsFragment =
            TransactionsFragment()
    }
}
