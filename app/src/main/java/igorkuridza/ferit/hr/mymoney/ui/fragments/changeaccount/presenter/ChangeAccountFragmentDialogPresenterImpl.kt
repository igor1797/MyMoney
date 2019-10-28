package igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.presenter

import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRepository
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRoomRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.view.ChangeAccountFragmentDialogView

class ChangeAccountFragmentDialogPresenterImpl(
    private val view: ChangeAccountFragmentDialogView,
    private val accountRepository: AccountRepository = AccountRoomRepository()
): ChangeAccountFragmentDialogPresenter{

    override fun onViewCreated() {
        view.setupUi()
    }

    override fun saveChanges(accountAmount: Float, account: Account, accountName: String) {
        if(accountAmount != account.amountOfMoney) accountRepository.updateAccountAmountOfMoney(accountAmount, account.id!!)
        if(accountName != account.name) accountRepository.updateAccountName(accountName, account.id!!)
    }
}