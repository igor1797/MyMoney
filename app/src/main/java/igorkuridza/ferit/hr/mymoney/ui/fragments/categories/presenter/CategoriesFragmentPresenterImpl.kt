package igorkuridza.ferit.hr.mymoney.ui.fragments.categories.presenter

import android.content.Context
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.getColorByRes
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.Transaction
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.model.recyclerview.CategoryAndAmount
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.view.CategoriesFragmentView

class CategoriesFragmentPresenterImpl(
    private val view: CategoriesFragmentView,
    private val transactionsRepository: TransactionRepository,
    private val categoriesRepository: CategoryRepository
): CategoriesFragmentPresenter {

    override fun onViewCreated() {
        view.initUi()
    }

    private fun getAllCategories(typeOfCategory: TypeOfCategory): List<Category> {
        return categoriesRepository.getAllCategories(typeOfCategory)
    }

    private fun getTransactions(categoryId: Long?, date: String): List<Transaction>{
        return transactionsRepository.getTransactionsByDateAndCategoryId(categoryId, date)
    }

    private fun getMapAmounts(typeOfCategory: TypeOfCategory, date: String): List<CategoryAndAmount>{
        val categories = getAllCategories(typeOfCategory)
        val amounts = mutableListOf<CategoryAndAmount>()
        var amount: Float
        categories.forEach { category ->
            amount = 0F
            getTransactions(category.categoryId, date).forEach{transaction ->
                amount += transaction.amountOfMoney
            }
            amounts.add(CategoryAndAmount(category, amount))
        }
        return amounts
    }

    private fun getDataForPieChart(typeOfCategory: TypeOfCategory,date: String): ArrayList<PieEntry>{
        val categories = getAllCategories(typeOfCategory)
        val list = arrayListOf<PieEntry>()
        list.clear()
        categories.forEach { category ->
            var amount = 0F
            getTransactions(category.categoryId, date).forEach{transaction ->
                amount += transaction.amountOfMoney
            }
            if(amount > 0) list.add(PieEntry(amount))
        }
        return list
    }

    private fun getAmounts(typeOfCategory: TypeOfCategory, date: String): List<Float>{
        val mapAmounts = getMapAmounts(typeOfCategory, date)
        val amounts = mutableListOf<Float>()
        mapAmounts.forEach {
            amounts.add(it.amount)
        }
        return amounts
    }

    private fun getTotalAmount(typeOfCategory: TypeOfCategory, date: String): Float{
        val amounts = getAmounts(typeOfCategory, date)
        var totalAmount = 0F
        amounts.forEach { amount->
            totalAmount += amount
        }
        return totalAmount
    }

    private fun getColorsForPieChart(typeOfCategory: TypeOfCategory, date: String,context: Context): ArrayList<Int>{
        val colors = arrayListOf<Int>()
        var category: Category
        val categoriesAndAmounts = getMapAmounts(typeOfCategory,date)
        categoriesAndAmounts.forEach {
            category = it.category
            if(it.amount > 0) colors.add(getColorByRes(context,category.color.getColor()))
        }
        return colors
    }

    override fun getPieData(typeOfCategory: TypeOfCategory,date: String,pieChart: PieChart){
        val set = PieDataSet(getDataForPieChart(typeOfCategory, date),"")
        set.apply {
            notifyDataSetChanged()
            colors = getColorsForPieChart(typeOfCategory, date, pieChart.context)
        }
        val pieData = PieData(set)
        pieData.apply {
            setValueFormatter(PercentFormatter(pieChart))
        }
        view.onGetPieData(pieData)
    }

    override fun getPieChartCenterText(date: String,isTypeCategoryExpenses: Boolean) {
        val centerText: String = if(isTypeCategoryExpenses)
            "TROŠKOVI "  +  "\n ${getTotalAmount(TypeOfCategory.EXPENSES, date)} kn"
        else
            "PRIMANJA" + "\n ${getTotalAmount(TypeOfCategory.INCOMES, date)} kn"
        view.onGetPieChartCenterText(centerText)
    }

    override fun getPieChartCenterTextColor(isTypeCategoryExpenses: Boolean, context: Context) {
        val color = if(isTypeCategoryExpenses)
            getColorByRes(context, R.color.red)
        else
            getColorByRes(context, R.color.green)
        view.onGetPieChartCenterTextColor(color)
    }

    override fun refreshAdapterData(typeOfCategory: TypeOfCategory, date: String) {
        view.onRefreshAdapterData(getMapAmounts(typeOfCategory,date))
    }
}