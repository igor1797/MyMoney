package igorkuridza.ferit.hr.mymoney.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igorkuridza.ferit.hr.mymoney.model.Transaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTransaction(transaction: Transaction)

    @Query("Select* FROM transactions ORDER BY timeOfTransaction DESC")
    fun getAllTransactions(): List<Transaction>

    @Query("Select* FROM transactions WHERE fullDate= :date")
    fun getAllTransactionsByDate(date: String): List<Transaction>

    @Query("Select* FROM transactions WHERE fullDate= :date AND note= :note")
    fun getAllTransactionsByDateAndNote(date: String, note: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE year= :year AND note= :note ORDER BY timeOfTransaction DESC")
    fun getTransactionsByYearAndNote(year: Int, note: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE monthYearDate= :monthYearDate AND note= :note ORDER BY timeOfTransaction DESC")
    fun getTransactionsByMonthYearAndNote(monthYearDate: String, note: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE categoryId= :id AND fullDate= :date")
    fun getTransactionsByDateAndCategoryId(id: Long?, date: String): List<Transaction>

    @Query("SELECT *FROM transactions WHERE note= :note")
    fun getAllTransactionsByNote(note: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE year= :year ORDER BY timeOfTransaction DESC")
    fun getTransactionsByYear(year: Int): List<Transaction>

    @Query("SELECT * FROM transactions WHERE categoryId= :categoryId AND monthYearDate= :monthYearDate ORDER BY timeOfTransaction DESC")
    fun getTransactionsByMonthYearCategoryId(categoryId: Long, monthYearDate: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE monthYearDate= :monthYearDate ORDER BY timeOfTransaction DESC")
    fun getTransactionsByMonthYear(monthYearDate: String): List<Transaction>

    @Query("SELECT * FROM transactions WHERE dayMonthDate= :dayMonthDate AND categoryId= :categoryId ORDER BY timeOfTransaction DESC")
    fun getTransactionsByDayMonthCategoryId(categoryId: Long,dayMonthDate: String): List<Transaction>
}