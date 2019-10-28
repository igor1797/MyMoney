package igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.presenter

import igorkuridza.ferit.hr.mymoney.helpers.iterator.collection.IAbstractCollection
import igorkuridza.ferit.hr.mymoney.helpers.iterator.IAbstractIterator
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.view.AddTransactionFragmentDialogView

class AddTransactionFragmentDialogPresenterImpl (
    private val view: AddTransactionFragmentDialogView,
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val accounts: IAbstractCollection,
    private var iterator: IAbstractIterator
): AddTransactionFragmentDialogPresenter {

    override fun onViewCreated() {
        view.initUi()
    }

    override fun setIterator() {
        accounts.setData(accountRepository.getAllAccounts())
        iterator = accounts.createIterator()
    }

    override fun saveTransaction() {
        view.onSaveTransaction(iterator.currentItem() as Account)
    }

    override fun addTransactionToDb(transaction: Transaction) {
        transactionRepository.addTransaction(transaction)
    }

    override fun updateAccountAmount(account: Account, amount: Float, isTypeOfCategoryExpense: Boolean) {
        if(isTypeOfCategoryExpense) accountRepository.updateAccountAmountOfMoney(account.amountOfMoney - amount, account.id!!)
        else accountRepository.updateAccountAmountOfMoney(account.amountOfMoney + amount, account.id!!)
    }

    override fun getFirstAccount() {
        view.onGetFirstAccount(iterator.first() as Account)
    }

    override fun getNextAccount() {
        view.onGetNextAccount(iterator.next() as Account)
    }
}