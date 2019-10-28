package igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.presenter

import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Transaction

interface AddTransactionFragmentDialogPresenter {

    fun onViewCreated()

    fun setIterator()

    fun saveTransaction()

    fun addTransactionToDb(transaction: Transaction)

    fun updateAccountAmount(account: Account, amount: Float, isTypeOfCategoryExpense: Boolean)

    fun getFirstAccount()

    fun getNextAccount()
}