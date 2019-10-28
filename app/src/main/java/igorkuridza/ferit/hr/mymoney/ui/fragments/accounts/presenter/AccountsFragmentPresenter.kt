package igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.presenter

interface AccountsFragmentPresenter {

    fun onViewCreated()

    fun getAccounts()

    fun getTotalAccountAmount()
}