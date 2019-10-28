package igorkuridza.ferit.hr.mymoney.ui.fragments.categories.presenter

import android.content.Context
import com.github.mikephil.charting.charts.PieChart
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

interface CategoriesFragmentPresenter {

    fun onViewCreated()

    fun refreshAdapterData(typeOfCategory: TypeOfCategory, date: String)

    fun getPieChartCenterText(date: String,isTypeCategoryExpenses: Boolean)

    fun getPieChartCenterTextColor(isTypeCategoryExpenses: Boolean, context: Context)

    fun getPieData(typeOfCategory: TypeOfCategory, date: String, pieChart: PieChart)
}