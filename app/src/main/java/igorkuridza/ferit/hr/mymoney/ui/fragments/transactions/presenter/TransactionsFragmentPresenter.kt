package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter


interface TransactionsFragmentPresenter {

        fun onViewCreated()

        fun getTransactionAdapterData(type: String)

        fun getTransactionByNote(note: String)
}