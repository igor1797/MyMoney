package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view

import igorkuridza.ferit.hr.mymoney.model.Transaction

interface TransactionsFragmentView{

    fun setupUi()

    fun onGetTransactionAdapterData(newData: List<Any>)
}