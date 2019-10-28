package igorkuridza.ferit.hr.mymoney.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import igorkuridza.ferit.hr.mymoney.database.dao.AccountDao
import igorkuridza.ferit.hr.mymoney.database.dao.CategoryDao
import igorkuridza.ferit.hr.mymoney.database.dao.TransactionDao
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.Transaction

@Database(entities = [Transaction::class, Category::class, Account::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DaoProvider: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun accountDao(): AccountDao

    companion object {
        private var instance: DaoProvider? = null

        fun getInstance(context: Context): DaoProvider{
            if(instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoProvider::class.java,
                    "TransactionDB"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as DaoProvider
        }
    }
}