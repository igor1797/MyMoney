package igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.presenter

import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view.AccountsFragmentView

class AccountsFragmentPresenterImpl(
    private val view: AccountsFragmentView,
    private val accountRepository: AccountRepository
): AccountsFragmentPresenter{

    override fun onViewCreated() {
        view.initUi()
    }

    private fun getAllAccounts(): List<Account>{
        return accountRepository.getAllAccounts()
    }

    override fun getAccounts() {
        view.onGetAccounts(getAllAccounts())
    }

    override fun getTotalAccountAmount() {
        val accounts = getAllAccounts()
        var totalAmount = 0F
        accounts.forEach {
            totalAmount += it.amountOfMoney
        }
        view.onGetTotalAccountAmount(totalAmount)
    }
}