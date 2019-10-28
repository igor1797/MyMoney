package igorkuridza.ferit.hr.mymoney.persistance.category_repository

import igorkuridza.ferit.hr.mymoney.MyMoneyApp
import igorkuridza.ferit.hr.mymoney.database.dao.CategoryDao
import igorkuridza.ferit.hr.mymoney.database.DaoProvider
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

class CategoryRoomRepository:
    CategoryRepository {

    private val db: DaoProvider = DaoProvider.getInstance(MyMoneyApp.getAppContext())

    private val categoryDao: CategoryDao = db.categoryDao()

    override fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }

    override fun getAllCategories(typeOfCategory: TypeOfCategory): List<Category> {
        return categoryDao.getAllCategories(typeOfCategory)
    }

    override fun getCategoryById(id: Long?): Category {
        return categoryDao.getCategoryById(id)
    }
}