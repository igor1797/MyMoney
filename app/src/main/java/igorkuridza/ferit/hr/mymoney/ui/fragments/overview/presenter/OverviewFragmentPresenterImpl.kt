package igorkuridza.ferit.hr.mymoney.ui.fragments.overview.presenter

import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarEntry
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view.OverviewFragmentView

class OverviewFragmentPresenterImpl(
    private val view: OverviewFragmentView,
    private val transactionsRepository: TransactionRepository,
    private val categoriesRepository: CategoryRepository
): OverviewFragmentPresenter {

    override fun onViewCreated() {
        view.setupUi()
    }

    override fun viewExpensesClicked() {
        view.onViewExpensesClick()
    }

    override fun viewIncomesClicked() {
        view.onViewIncomesClick()
    }

    override fun showSelectDialog() {
        view.onShowSelectDialog()
    }

    override fun getLabelsForCurrentMonth(){
        val labels = ArrayList<String>()
        val currentDayOfMonth = getCurrentDayOfMonth()
        for(day in 0..currentDayOfMonth)
            labels.add(day.toString())

        view.onGetLabelsForCurrentMonth(labels)
    }

    private fun getAllMonths(): ArrayList<String>{
        return arrayListOf(
            "siječnja","veljače","ožujka",
            "travnja","svibnja","lipnja",
            "srpnja", "kolovoza", "rujna",
            "listopada","studenoga", "prosinca")
    }

    private fun getLabelsForCurYear(): List<String>{
        val labels = arrayListOf<String>()
        val months = getAllMonths()
        labels.add("")
        for(br in 0 until getCurrentMonthNumber()) labels.add(months[br])
        return labels
    }

    override fun getLabelsForCurrentYear() {
        val labels = getLabelsForCurYear()
        view.onGetLabelsForCurrentYear(labels)
    }

    private fun getDataForBarChartCurrentMonth(typeOfCategory: TypeOfCategory): ArrayList<BarEntry>{
        val categories = categoriesRepository.getAllCategories(typeOfCategory)
        val currentMonth = getCurrentMonth()
        val yValues = ArrayList<Float>()
        val barEntries = ArrayList<BarEntry>()
        val currentDayOfMonth = getCurrentDayOfMonth()
        for(day in 1..currentDayOfMonth) {
            val date = "$day $currentMonth"
            yValues.clear()
            categories.forEach { category->
                var amount = 0F
                transactionsRepository.getTransactionsByDateAndCategoryId(category.categoryId!!, date)
                    .forEach { transaction ->
                        amount += transaction.amountOfMoney
                    }
                yValues.add(amount)
            }
            barEntries.add(BarEntry(day.toFloat(),yValues.toFloatArray()))
        }
        return barEntries
    }

    private fun getDataForBarChartCurrentYear(typeOfCategory: TypeOfCategory): ArrayList<BarEntry>{
        val categories = categoriesRepository.getAllCategories(typeOfCategory)
        val months = getLabelsForCurYear()
        val yValues = ArrayList<Float>()
        val barEntries = ArrayList<BarEntry>()
        for((br, month) in months.withIndex()) {
            yValues.clear()
            categories.forEach { category->
                var amount = 0F
                transactionsRepository.getTransactionsByMonthYearCategoryId(category.categoryId!!,"$month ${getCurrentYear()}")
                    .forEach { transaction ->
                        amount += transaction.amountOfMoney
                    }
                yValues.add(amount)
            }
            barEntries.add(BarEntry(br.toFloat(),yValues.toFloatArray()))
        }
        return barEntries
    }

    override fun getAxisLeftMaximumCurrentMonth(typeOfCategory: TypeOfCategory){
        val categories = categoriesRepository.getAllCategories(typeOfCategory)
        val currentMonth = getCurrentMonth()
        var axisLeftMaximum = 0F
        var amount: Float
        val currentDayOfMonth = getCurrentDayOfMonth()
        for(day in 1..currentDayOfMonth) {
            val date = "$day $currentMonth"
            amount = 0F
            categories.forEach { category->
                transactionsRepository.getTransactionsByDateAndCategoryId(category.categoryId!!, date)
                    .forEach { transaction ->
                        amount += transaction.amountOfMoney
                    }
            }
            if(amount > axisLeftMaximum) axisLeftMaximum = amount
        }
        view.onGetAxisLeftMaximumCurrentMonth(axisLeftMaximum)
    }

    override fun getAxisLeftMaximumCurrentYear(typeOfCategory: TypeOfCategory){
        val categories = categoriesRepository.getAllCategories(typeOfCategory)
        var axisLeftMaximum = 0F
        var amount: Float
        val months = getLabelsForCurYear()
        for(month in months) {
            amount = 0F
            categories.forEach { category->
                transactionsRepository.getTransactionsByMonthYearCategoryId(category.categoryId!!,"$month ${getCurrentYear()}")
                    .forEach { transaction ->
                        amount += transaction.amountOfMoney
                    }
            }
            if(amount > axisLeftMaximum) axisLeftMaximum = amount
        }
        view.onGetAxisLeftMaximumCurrentYear(axisLeftMaximum)
    }

    private fun getAllCategories(typeOfCategory: TypeOfCategory): List<Category> {
        return categoriesRepository.getAllCategories(typeOfCategory)
    }

    private fun getStackedLabelsForHorizontalBarChart(typeOfCategory: TypeOfCategory): Array<String>{
        val stackedLabels = mutableListOf<String>()
        val categories = getAllCategories(typeOfCategory)
        categories.forEach {category ->
            stackedLabels.add(category.name)
        }
        return stackedLabels.toTypedArray()
    }

    private fun getColorsForHorizontalBarChart(barChart: HorizontalBarChart, typeOfCategory: TypeOfCategory): ArrayList<Int>{
        val colors = arrayListOf<Int>()
        val categories = getAllCategories(typeOfCategory)
        categories.forEach {category ->
            colors.add(ContextCompat.getColor(barChart.context, category.color.getColor()))
        }
        return colors
    }

    override fun getDataForBarChart(barChart: HorizontalBarChart, typeOfCategory: TypeOfCategory, typeData: String) {
        when(typeData){
            TYPE_CURRENT_MONTH-> {
                val entries = getDataForBarChartCurrentMonth(typeOfCategory)
                val stackLabels = getStackedLabelsForHorizontalBarChart(typeOfCategory)
                val colors = getColorsForHorizontalBarChart(barChart, typeOfCategory)
                view.onGetDataForBarChart(entries, stackLabels, colors)
            }
            TYPE_CURRENT_YEAR->{
                val entries = getDataForBarChartCurrentYear(typeOfCategory)
                val stackLabels = getStackedLabelsForHorizontalBarChart(typeOfCategory)
                val colors = getColorsForHorizontalBarChart(barChart, typeOfCategory)
                view.onGetDataForBarChart(entries, stackLabels, colors)
            }
        }
    }
}