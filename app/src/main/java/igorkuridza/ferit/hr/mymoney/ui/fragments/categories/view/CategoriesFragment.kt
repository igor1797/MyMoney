package igorkuridza.ferit.hr.mymoney.ui.fragments.categories.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mikephil.charting.data.*
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.helpers.DateChangeViewModel
import igorkuridza.ferit.hr.mymoney.model.*
import igorkuridza.ferit.hr.mymoney.model.recyclerview.CategoryAndAmount
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.OnRecyclerViewItemClickListener
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter.RecyclerAdapter
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.view.AddTransactionFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.AddTransactionListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.presenter.CategoriesFragmentPresenter
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CategoriesFragment: CategoriesFragmentView, BaseFragment(), AddTransactionListener,
    OnRecyclerViewItemClickListener {

    private val presenter: CategoriesFragmentPresenter by inject { parametersOf(this) }
    private val categoriesAdapter by lazy { RecyclerAdapter() }
    private lateinit var typeOfCategory: TypeOfCategory
    private lateinit var date: String
    private val SPANCOUNT = 3

    override fun getLayoutResourceId() = R.layout.fragment_categories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun initUi() {
        typeOfCategory = arguments?.get(TYPE_OF_CATEGORY_KEY) as TypeOfCategory
        date = getTodayDate()
        categoriesAdapter.setListener(this)
        val model = ViewModelProviders.of(activity!!).get(DateChangeViewModel::class.java)
        model.date.observe(this, Observer<String> { date->
            this.date = date
            refreshData()
            setPieChartData()
        })
        setRecyclerView()
        refreshData()
        setPieChart()
        setPieChartData()
    }

    private fun setRecyclerView(){
        recyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(context, SPANCOUNT)
        }
    }

    private fun setPieChart(){
        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            legend.isEnabled = false
            holeRadius = 75F
            animateY(500)
            setCenterTextSize(15F)
        }
    }

    private fun setPieChartData(){
        presenter.getPieData(typeOfCategory, date, pieChart)
        setPieChartCenterText()
        setPieChartCenterColor()
    }

    override fun onGetPieData(pieData: PieData) {
        pieChart.apply {
            notifyDataSetChanged()
            invalidate()
            data = pieData
            animateY(500)
        }
    }

    private fun isTypeOfCategoryExpenses(): Boolean{
        if(typeOfCategory == TypeOfCategory.EXPENSES) return true
        return false
    }

    private fun setPieChartCenterText(){
        presenter.getPieChartCenterText(date, isTypeOfCategoryExpenses())
    }

    override fun onGetPieChartCenterText(centerText: String) {
        pieChart.centerText = centerText
    }

    private fun setPieChartCenterColor(){
        presenter.getPieChartCenterTextColor(isTypeOfCategoryExpenses(), context!!)
    }

    override fun onGetPieChartCenterTextColor(color: Int) {
        pieChart.setCenterTextColor(color)
    }

    private fun refreshData(){
        presenter.refreshAdapterData(typeOfCategory, date)
    }

    override fun onRefreshAdapterData(mapAmount: List<CategoryAndAmount>) {
        categoriesAdapter.swapData(mapAmount)
    }

    override fun onAddTransaction() {
        refreshData()
        setPieChartData()
    }

    override fun onItemClick(item: Any) {
        val fragmentManager = fragmentManager
        val addTransactionFragmentDialog = AddTransactionFragmentDialog.newInstance(item as Category, date)
        addTransactionFragmentDialog.setAddTransactionListener(this)
        addTransactionFragmentDialog.show(fragmentManager!!, "")
    }

    companion object{
        fun newInstance(typeOfCategory: TypeOfCategory): CategoriesFragment {
            val bundle = Bundle().apply {
                putParcelable(TYPE_OF_CATEGORY_KEY, typeOfCategory)
            }
            return CategoriesFragment()
                .apply { arguments = bundle }
        }
    }
}
