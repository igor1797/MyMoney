package igorkuridza.ferit.hr.mymoney.ui.activities.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.showFragment
import igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter.MainActivityPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view.AccountsFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view.BalanceFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.ViewPagerFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view.OverviewFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view.TransactionsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter: MainActivityPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
    }

    override fun initUi() {
        presenter.getFirstStartOfApp()
        bottomNavigation.selectedItemId = R.id.item_categories
        showFragment(R.id.fragmentContainer, ViewPagerFragment.newInstance())
        bottomNavigation.setOnNavigationItemSelectedListener { openFragment(it) }
    }

    override fun onGetFirstStartOfApp(firstStartOfApp: Boolean) {
        if(firstStartOfApp) {
            presenter.apply {
                saveCategoriesAndAccountsToDb()
                saveFirstStartOfApp(false)
            }
        }
    }

    private fun openFragment(menuItem: MenuItem): Boolean {
        var selected : Fragment? = null
        when(menuItem.itemId){
            R.id.item_categories -> selected = ViewPagerFragment.newInstance()
            R.id.item_balance -> selected = BalanceFragment.newInstance()
            R.id.item_overview -> selected = OverviewFragment.newInstance()
            R.id.item_accounts -> selected = AccountsFragment.newInstance()
            R.id.item_transactions -> selected = TransactionsFragment.newInstance()
        }
        if(selected!=null)
            showFragment(R.id.fragmentContainer, selected)
        return true
    }
}
