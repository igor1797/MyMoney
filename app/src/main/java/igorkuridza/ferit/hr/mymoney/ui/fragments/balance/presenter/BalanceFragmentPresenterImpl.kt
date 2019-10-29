package igorkuridza.ferit.hr.mymoney.ui.fragments.balance.presenter

import igorkuridza.ferit.hr.mymoney.common.getCurrentMonth
import igorkuridza.ferit.hr.mymoney.common.getMonthFromMonthYear
import igorkuridza.ferit.hr.mymoney.common.getYearFromMonthYear
import igorkuridza.ferit.hr.mymoney.helpers.iterator.collection.IAbstractCollection
import igorkuridza.ferit.hr.mymoney.helpers.iterator.IAbstractIterator
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.Months
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.model.recyclerview.MonthYearBalance
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view.BalanceFragmentView

class BalanceFragmentPresenterImpl (
    private val view: BalanceFragmentView,
    private val transactionsRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val months: IAbstractCollection,
    private var iterator: IAbstractIterator
): BalanceFragmentPresenter {

    override fun onViewCreated() {
        view.setupUi()
    }

    override fun setIterator() {
        months.setData(Months.getAll())
        iterator = months.createIterator()
    }

    override fun getDataForAdapter(){
        val data = ArrayList<Any>()
        val months = Months.getAll()

        months.forEach {monthYear->
            val month = getMonthFromMonthYear(monthYear)
            val year = getYearFromMonthYear(monthYear)
            val incomesTotalAmount = getTotalAmountForTypeOfCategory(TypeOfCategory.INCOMES, monthYear)
            val expensesTotalAmount = getTotalAmountForTypeOfCategory(TypeOfCategory.EXPENSES, monthYear)
            data.add(MonthYearBalance(month, year, expensesTotalAmount, incomesTotalAmount))
            data.add(incomesTotalAmount - expensesTotalAmount)
        }
        view.onGetDataForAdapter(data)
    }

    private fun getAllCategoriesByType(typeOfCategory: TypeOfCategory): List<Category>{
        return categoryRepository.getAllCategories(typeOfCategory)
    }

    private fun getAllTransactionsByMonthYearCategoryId(id: Long, monthYear: String): List<Transaction>{
        return transactionsRepository.getTransactionsByMonthYearCategoryId(id,monthYear)
    }

    private fun getTotalAmountForTypeOfCategory(typeOfCategory: TypeOfCategory, monthYear: String): Float{
        val categories = getAllCategoriesByType(typeOfCategory)
        var amount = 0F
        categories.forEach {category->
            getAllTransactionsByMonthYearCategoryId(category.categoryId!!, monthYear).forEach {transaction ->
                amount += transaction.amountOfMoney
            }
        }
        return amount
    }
}
