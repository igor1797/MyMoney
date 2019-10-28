package igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.presenter

import igorkuridza.ferit.hr.mymoney.model.Account

interface ChangeAccountFragmentDialogPresenter {

    fun onViewCreated()

    fun saveChanges(accountAmount: Float, account: Account, accountName: String)
}