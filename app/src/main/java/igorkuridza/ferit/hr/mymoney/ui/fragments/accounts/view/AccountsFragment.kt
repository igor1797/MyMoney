package igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.joinStrings
import igorkuridza.ferit.hr.mymoney.common.setDivider
import igorkuridza.ferit.hr.mymoney.common.setTextViewTextColor
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.adapter.RecyclerAdapter
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.presenter.AccountsFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.view.ChangeAccountFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.ChangeAccountListener
import igorkuridza.ferit.hr.mymoney.ui.recyclerview.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.fragment_accounts.*
import kotlinx.android.synthetic.main.fragment_categories.recyclerView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AccountsFragment: BaseFragment(), AccountsFragmentView,
    OnRecyclerViewItemClickListener, ChangeAccountListener{

    private val presenter: AccountsFragmentPresenter by inject { parametersOf(this) }
    private val accountAdapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun getLayoutResourceId() = R.layout.fragment_accounts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun initUi() {
        accountAdapter.setListener(this)
        setupRecyclerView()
        refreshData()
    }

    private fun refreshData(){
        presenter.apply {
            getAccounts()
            getTotalAccountAmount()
        }
    }
    private fun setupRecyclerView(){
        recyclerView.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(context)
            setDivider()
        }
    }

    override fun onGetAccounts(accounts: List<Account>) {
        accountAdapter.swapData(accounts)
    }

    override fun onGetTotalAccountAmount(totalAccountAmount: Float) {
        tvAccountTotalAmount.apply {
            text = joinStrings(totalAccountAmount.toString() ," kn")
            setTextViewTextColor(getTextViewTextColor(totalAccountAmount))
        }
    }

    private fun getTextViewTextColor(totalAccountAmount: Float): Int{
        return when{
            totalAccountAmount < 0 -> R.color.red
            totalAccountAmount > 0 -> R.color.green
            else -> R.color.colorBlackTrans
        }
    }

    override fun onItemClick(item: Any) {
        val fragmentManager = fragmentManager
        val changeAccountFragmentDialog = ChangeAccountFragmentDialog.newInstance(item as Account)
        changeAccountFragmentDialog.setChangeAccountListener(this)
        changeAccountFragmentDialog.show(fragmentManager!!, "")
    }

    override fun onChangeAccountListener() {
        refreshData()
    }

    companion object{
        fun newInstance(): AccountsFragment =
            AccountsFragment()
    }
}