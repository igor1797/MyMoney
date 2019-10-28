package igorkuridza.ferit.hr.mymoney.persistance.account_repository

import igorkuridza.ferit.hr.mymoney.MyMoneyApp
import igorkuridza.ferit.hr.mymoney.database.dao.AccountDao
import igorkuridza.ferit.hr.mymoney.database.DaoProvider
import igorkuridza.ferit.hr.mymoney.model.Account

class AccountRoomRepository:
    AccountRepository {

    private val db: DaoProvider = DaoProvider.getInstance(MyMoneyApp.getAppContext())

    private val accountDao: AccountDao = db.accountDao()

    override fun addAccount(account: Account) {
        accountDao.addAccount(account)
    }

    override fun getAllAccounts(): List<Account> {
        return accountDao.getAllAccounts()
    }

    override fun updateAccountAmountOfMoney(amountOfMoney: Float, id: Long) {
        accountDao.updateAccountAmountOfMoney(amountOfMoney,id)
    }

    override fun updateAccountColor(accountColor: Int, id: Long) {
        accountDao.updateAccountColor(accountColor, id)
    }

    override fun updateAccountName(name: String, id: Long) {
        accountDao.updateAccountName(name, id)
    }

    override fun getAccountByName(name: String): Account {
        return accountDao.getAccountByName(name)
    }
}