package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter

import igorkuridza.ferit.hr.mymoney.model.Transaction


interface TransactionsFragmentPresenter {

        fun onViewCreated()

        fun getTransactionAdapterData(dateType: String)

        fun filterDataByNote(dateType: String, searchText: String?)
}