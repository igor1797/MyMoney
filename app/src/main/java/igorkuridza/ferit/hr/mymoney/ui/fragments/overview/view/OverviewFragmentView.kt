package igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view

import com.github.mikephil.charting.data.BarEntry

interface OverviewFragmentView {

    fun setupUi()

    fun onViewExpensesClick()

    fun onViewIncomesClick()

    fun onShowSelectDialog()

    fun onGetDataForBarChart(entries: List<BarEntry>, stackLabels: Array<String>, colors: List<Int>)

    fun onGetAxisLeftMaximumCurrentMonth(axisLeftMaximum: Float)

    fun onGetLabelsForCurrentMonth(xAxisLabels: List<String>)

    fun onGetAxisLeftMaximumCurrentYear(axisLeftMaximum: Float)

    fun onGetLabelsForCurrentYear(xAxisLabels: List<String>)
}