package igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view

import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.presenter.OverviewFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectFragmentDialog2
import igorkuridza.ferit.hr.mymoney.ui.fragments.select.SelectListener
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.fragment_overview.tvDate
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class OverviewFragment : BaseFragment(), SelectListener, OverviewFragmentView {

    private val presenter: OverviewFragmentPresenter by inject { parametersOf(this) }

    override fun getLayoutResourceId() = R.layout.fragment_overview

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setupUi() {
        tvDate.text = getCurrentMonth()
        viewExpenses.setOnClickListener { presenter.viewExpensesClicked() }
        viewIncomes.setOnClickListener { presenter.viewIncomesClicked() }
        ivShowSelect.setOnClickListener { presenter.showSelectDialog() }
        setHorizontalBarChart()
        setBarChart(TypeOfCategory.EXPENSES)
    }

    override fun onShowSelectDialog(){
        val fragmentManager = fragmentManager
        val selectDialog = SelectFragmentDialog2.newInstance()
        selectDialog.show(fragmentManager!!, "")
        selectDialog.setSelectListener(this)
    }

    override fun onViewExpensesClick(){
        setBarChart(TypeOfCategory.EXPENSES)
        viewExpenses.setViewBackgroundColor(R.color.red)
        viewIncomes.setViewBackgroundColor(R.color.colorWhite)
        tvExpenses.setTextViewTextColor(R.color.colorWhite)
        tvIncomes.setTextViewTextColor(R.color.colorBlack)
    }

    override fun onViewIncomesClick(){
        setBarChart(TypeOfCategory.INCOMES)
        viewExpenses.setViewBackgroundColor(R.color.colorWhite)
        viewIncomes.setViewBackgroundColor(R.color.green)
        tvExpenses.setTextViewTextColor(R.color.colorBlack)
        tvIncomes.setTextViewTextColor(R.color.colorWhite)
    }

    private fun setBarChart(typeOfCategory: TypeOfCategory){
        if(tvDate.text == getCurrentMonth()){
            presenter.apply {
                getAxisLeftMaximumCurrentMonth(typeOfCategory)
                getLabelsForCurrentMonth()
                getDataForBarChart(barChart, typeOfCategory, TYPE_CURRENT_MONTH)
            }
            setHorizontalBarChartValues(getCurrentDayOfMonth())
        }
        else{
            presenter.apply {
                getDataForBarChart(barChart, typeOfCategory, TYPE_CURRENT_YEAR)
                getAxisLeftMaximumCurrentYear(typeOfCategory)
                getLabelsForCurrentYear()
            }
        }
    }

    override fun onGetDataForBarChart(entries: List<BarEntry>, stackLabels: Array<String>, colors: List<Int>) {
        val barDateSet = BarDataSet(entries, "")
        barDateSet.stackLabels = stackLabels
        barDateSet.colors = colors
        val barData = BarData(barDateSet)
        if (getCurrentDayOfMonth() < 5) barData.barWidth = 0.2F
        barChart.data = barData
    }

    private fun setHorizontalBarChart(){
        barChart.apply {
            setDrawGridBackground(false)
            isDoubleTapToZoomEnabled = false
            setFitBars(true)
            isScaleYEnabled = false
            legend.apply {
                form = Legend.LegendForm.CIRCLE
                verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                orientation = Legend.LegendOrientation.VERTICAL
                textSize = 5F
            }
            xAxis.apply {
                setDrawGridLines(false)
                setDrawLabels(true)
                setDrawValueAboveBar(false)
                axisMinimum = 0F
                position = XAxis.XAxisPosition.BOTTOM
            }
            axisLeft.apply {
                axisMinimum = 0F
                setDrawGridLines(false)
            }
            axisRight.isEnabled = false
            description.isEnabled = false
        }
    }

    override fun onGetAxisLeftMaximumCurrentMonth(axisLeftMaximum: Float) {
        barChart.axisLeft.axisMaximum = axisLeftMaximum
    }

    override fun onGetLabelsForCurrentMonth(xAxisLabels: List<String>) {
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
    }

    private fun setHorizontalBarChartValues(labelCount: Int) {
        barChart.apply {
            notifyDataSetChanged()
            invalidate()
            xAxis.setLabelCount(labelCount, false)
        }
    }

    override fun onGetLabelsForCurrentYear(xAxisLabels: List<String>) {
        setHorizontalBarChartValues(xAxisLabels.size)
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
    }

    override fun onGetAxisLeftMaximumCurrentYear(axisLeftMaximum: Float) {
        barChart.axisLeft.axisMaximum = axisLeftMaximum
    }

    override fun onSelect(type: String) {
        when(type){
            TYPE_CURRENT_MONTH -> {
                tvDate.text = getCurrentMonth()
                setHorizontalBarChartValues(getCurrentDayOfMonth())
                presenter.getDataForBarChart(barChart, TypeOfCategory.EXPENSES, TYPE_CURRENT_MONTH)
                onViewExpensesClick()
            }
            TYPE_CURRENT_YEAR -> {
                tvDate.text = getCurrentYear()
                presenter.apply {
                    getDataForBarChart(barChart, TypeOfCategory.EXPENSES, TYPE_CURRENT_YEAR)
                    getAxisLeftMaximumCurrentYear(TypeOfCategory.EXPENSES)
                    getLabelsForCurrentYear()
                }
                onViewExpensesClick()
            }
        }
    }

    companion object{
        fun newInstance(): OverviewFragment =
            OverviewFragment()
    }
}