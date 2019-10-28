package igorkuridza.ferit.hr.mymoney.persistance.category_repository

import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

interface CategoryRepository {

    fun addCategory(category: Category)

    fun getAllCategories(typeOfCategory: TypeOfCategory): List<Category>

    fun getCategoryById(id: Long?): Category
}