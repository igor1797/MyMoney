package igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.view

import igorkuridza.ferit.hr.mymoney.model.Account

interface AddTransactionFragmentDialogView {

    fun initUi()

    fun onGetFirstAccount(account: Account)

    fun onGetNextAccount(account: Account)

    fun onSaveTransaction(account: Account)
}