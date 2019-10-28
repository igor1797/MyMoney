package igorkuridza.ferit.hr.mymoney.persistance.transaction_repository

import igorkuridza.ferit.hr.mymoney.model.Transaction

interface TransactionRepository {

    fun addTransaction(transaction: Transaction)

    fun getAllTransactions(): List<Transaction>

    fun getAllTransactionsByDate(date: String): List<Transaction>

    fun getTransactionsByDateAndCategoryId(id: Long?, date: String): List<Transaction>

    fun getTransactionsByYear(year: Int): List<Transaction>

    fun getTransactionsByMonthYearCategoryId(categoryId: Long, monthYearDate: String): List<Transaction>

    fun getTransactionsByMonthYear(monthYearDate: String): List<Transaction>

    fun getTransactionsByDayMonthCategoryId(categoryId: Long,dayMonthDate: String): List<Transaction>

    fun getAllTransactionsByNote(note: String): List<Transaction>

    fun getAllTransactionsByDateAndNote(date: String, note: String): List<Transaction>

    fun getTransactionsByYearAndNote(year: Int, note: String): List<Transaction>

    fun getTransactionsByMonthYearAndNote(monthYearDate: String, note: String): List<Transaction>
}