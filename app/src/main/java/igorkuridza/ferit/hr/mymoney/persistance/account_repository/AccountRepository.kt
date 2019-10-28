package igorkuridza.ferit.hr.mymoney.persistance.account_repository

import igorkuridza.ferit.hr.mymoney.model.Account

interface AccountRepository {

    fun addAccount(account: Account)

    fun getAllAccounts(): List<Account>

    fun updateAccountAmountOfMoney(amountOfMoney: Float, id: Long)

    fun updateAccountName(name: String, id: Long)

    fun updateAccountColor(accountColor: Int, id: Long)

    fun getAccountByName(name: String): Account
}