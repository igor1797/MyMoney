package igorkuridza.ferit.hr.mymoney.common

import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.Transaction

fun convertToTransaction(category: Category, date: String, amountOfMoney: Float, account: Account, note: String): Transaction{
    return Transaction(category = category,
    timeOfTransaction = convertDateToMilliseconds(date),
    amountOfMoney = amountOfMoney,
    account = account,
    note = note,
    day = getDay(date),
    month = getMonth(date),
    year = getYear(date)
    )
}