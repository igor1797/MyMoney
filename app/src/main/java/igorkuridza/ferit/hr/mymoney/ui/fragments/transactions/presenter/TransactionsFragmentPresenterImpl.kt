package igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter

import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view.TransactionsFragmentView
import java.util.*

class TransactionsFragmentPresenterImpl(
    private val view: TransactionsFragmentView,
    private val transactionsRepository: TransactionRepository
): TransactionsFragmentPresenter {

    override fun onViewCreated() {
        view.setupUi()
    }

    private fun getTransactionsFromToday(): List<Transaction>{
        val todayDate = getTodayDate()
        return transactionsRepository.getAllTransactionsByDate(todayDate)
    }

    private fun getAllTransactions(): List<Transaction>{
        return transactionsRepository.getAllTransactions()
    }

    private fun getTransactionsByYear(year: Int): List<Transaction>{
        return transactionsRepository.getTransactionsByYear(year)
    }

    private fun getTransactionsByMonthYear(monthYear: String): List<Transaction>{
        return transactionsRepository.getTransactionsByMonthYear(monthYear)
    }

    private fun getNewDataForAdapter(transactions: List<Transaction>): List<Any>{
        val newData = arrayListOf<Any>()
        if(transactions.isNotEmpty()) {
            var date = transactions.first().fullDate
            newData.add(convertDate(date))
            transactions.forEach { transaction ->
                if (date != transaction.fullDate) {
                    newData.add(convertDate(transaction.fullDate))
                }
                date = transaction.fullDate
                newData.add(transaction)
            }
        }
        return newData
    }

    private fun getTransactionsByDateType(dateType: String): List<Transaction>{
        when(dateType){
            TYPE_ALL_TIME -> return getAllTransactions()
            TYPE_TODAY -> return getTransactionsFromToday()
            TYPE_CURRENT_MONTH ->{
                val currentMonth = getCurrentMonth()
                return getTransactionsByMonthYear(currentMonth)
            }
            TYPE_CURRENT_YEAR ->{
                val currentYear = getCurrentYear().toInt()
                return getTransactionsByYear(currentYear)
            }
        }
        return emptyList()
    }

    override fun getTransactionAdapterData(dateType: String) {
        val transactions = getTransactionsByDateType(dateType)
        val newDataForAdapter = getNewDataForAdapter(transactions)
        view.onGetTransactionAdapterData(newDataForAdapter)
    }

    override fun filterDataByNote(dateType: String, searchText: String?) {
        val transactions = getTransactionsByDateType(dateType).filter {
            it.note.toLowerCase(Locale.ROOT).contains(searchText!!.toLowerCase(Locale.ROOT))
        }
        val newDataForAdapter = getNewDataForAdapter(transactions)
        view.onGetTransactionAdapterData(newDataForAdapter)
    }
}