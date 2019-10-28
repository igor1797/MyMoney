package igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view

interface BalanceFragmentView {

    fun setupUi()

    fun onGetDataForAdapter(data: List<Any>)
}