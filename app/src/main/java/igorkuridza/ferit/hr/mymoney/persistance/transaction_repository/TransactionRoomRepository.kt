package igorkuridza.ferit.hr.mymoney.persistance.transaction_repository

import igorkuridza.ferit.hr.mymoney.MyMoneyApp
import igorkuridza.ferit.hr.mymoney.database.DaoProvider
import igorkuridza.ferit.hr.mymoney.database.dao.TransactionDao
import igorkuridza.ferit.hr.mymoney.model.Transaction

class TransactionRoomRepository: TransactionRepository {

    private var db: DaoProvider = DaoProvider.getInstance(MyMoneyApp.getAppContext())

    private var transactionDao: TransactionDao = db.transactionDao()

    override fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAllTransactions()
    }

    override fun addTransaction(transaction: Transaction) {
        transactionDao.addTransaction(transaction)
    }

    override fun getAllTransactionsByDate(date: String): List<Transaction> {
        return transactionDao.getAllTransactionsByDate(date)
    }

    override fun getTransactionsByDateAndCategoryId(id: Long?, date: String): List<Transaction> {
        return transactionDao.getTransactionsByDateAndCategoryId(id, date)
    }

    override fun getTransactionsByMonthYearCategoryId(categoryId: Long, monthYearDate: String): List<Transaction> {
        return transactionDao.getTransactionsByMonthYearCategoryId(categoryId, monthYearDate)
    }

    override fun getTransactionsByYear(year: Int): List<Transaction> {
        return transactionDao.getTransactionsByYear(year)
    }

    override fun getTransactionsByMonthYear(monthYearDate: String): List<Transaction> {
        return transactionDao.getTransactionsByMonthYear(monthYearDate)
    }

    override fun getTransactionsByDayMonthCategoryId(categoryId: Long, dayMonthDate: String): List<Transaction> {
        return transactionDao.getTransactionsByDayMonthCategoryId(categoryId, dayMonthDate)
    }

    override fun getAllTransactionsByNote(note: String): List<Transaction>{
        return transactionDao.getAllTransactionsByNote(note)
    }

    override fun getAllTransactionsByDateAndNote(date: String, note: String): List<Transaction> {
        return transactionDao.getAllTransactionsByDateAndNote(date, note)
    }

    override fun getTransactionsByMonthYearAndNote(monthYearDate: String, note: String): List<Transaction> {
        return transactionDao.getTransactionsByMonthYearAndNote(monthYearDate, note)
    }

    override fun getTransactionsByYearAndNote(year: Int, note: String): List<Transaction> {
        return transactionDao.getTransactionsByYearAndNote(year, note)
    }
}