package igorkuridza.ferit.hr.mymoney.ui.fragments.categories.view

import com.github.mikephil.charting.data.PieData
import igorkuridza.ferit.hr.mymoney.model.recyclerview.CategoryAndAmount

interface CategoriesFragmentView {

    fun initUi()

    fun onRefreshAdapterData(mapAmount: List<CategoryAndAmount>)

    fun onGetPieChartCenterText(centerText: String)

    fun onGetPieChartCenterTextColor(color: Int)

    fun onGetPieData(pieData: PieData)
}