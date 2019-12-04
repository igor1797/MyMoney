package igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.getCurrentYear
import igorkuridza.ferit.hr.mymoney.common.setDivider
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter.RecyclerAdapter
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.presenter.BalanceFragmentPresenter
import kotlinx.android.synthetic.main.fragment_transactions.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class BalanceFragment: BaseFragment(), BalanceFragmentView{

    private val presenter: BalanceFragmentPresenter by inject { parametersOf(this) }
    private val balanceAdapter by lazy { RecyclerAdapter() }

    override fun getLayoutResourceId() = R.layout.fragment_balance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setupUi() {
        tvDate.text = getCurrentYear()
        setupRecyclerView()
        presenter.apply {
            setIterator()
            getDataForAdapter()
        }
    }

    override fun onGetDataForAdapter(data: List<Any>) {
        balanceAdapter.swapData(data)
    }

    private fun setupRecyclerView(){
        recyclerView.apply {
            adapter = balanceAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object{
        fun newInstance(): BalanceFragment =
            BalanceFragment()
    }
}