package igorkuridza.ferit.hr.mymoney.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igorkuridza.ferit.hr.mymoney.model.Account

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAccount(account: Account)

    @Query("SELECT *FROM account")
    fun getAllAccounts(): List<Account>

    @Query("UPDATE account SET accountAmountOfMoney= :amountOfMoney WHERE id= :id")
    fun updateAccountAmountOfMoney(amountOfMoney: Float, id: Long)

    @Query("UPDATE account SET accountColor= :accountColor WHERE id= :id")
    fun updateAccountColor(accountColor: Int, id: Long)

    @Query("UPDATE account SET accountName= :name WHERE id= :id")
    fun updateAccountName(name: String, id: Long)

    @Query("SELECT * FROM account WHERE accountName= :name")
    fun getAccountByName(name: String): Account
}