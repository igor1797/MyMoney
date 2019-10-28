package igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view

import igorkuridza.ferit.hr.mymoney.model.Account

interface AccountsFragmentView {

    fun initUi()

    fun onGetAccounts(accounts: List<Account>)

    fun onGetTotalAccountAmount(totalAccountAmount: Float)
}