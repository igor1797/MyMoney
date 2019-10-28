package igorkuridza.ferit.hr.mymoney.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(category: Category)

    @Query("SELECT * FROM Category WHERE typeOfCategory= :typeOfCat")
    fun getAllCategories(typeOfCat: TypeOfCategory): List<Category>

    @Query("SELECT * FROM category WHERE categoryId= :id")
    fun getCategoryById(id: Long?): Category
}