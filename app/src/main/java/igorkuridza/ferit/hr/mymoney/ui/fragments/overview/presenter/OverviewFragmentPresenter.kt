package igorkuridza.ferit.hr.mymoney.ui.fragments.overview.presenter

import com.github.mikephil.charting.charts.HorizontalBarChart
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

interface OverviewFragmentPresenter {

    fun onViewCreated()

    fun viewExpensesClicked()

    fun viewIncomesClicked()

    fun showSelectDialog()

    fun getDataForBarChart(barChart: HorizontalBarChart, typeOfCategory: TypeOfCategory, typeData: String)

    fun getAxisLeftMaximumCurrentMonth(typeOfCategory: TypeOfCategory)

    fun getLabelsForCurrentMonth()

    fun getAxisLeftMaximumCurrentYear(typeOfCategory: TypeOfCategory)

    fun getLabelsForCurrentYear()
}