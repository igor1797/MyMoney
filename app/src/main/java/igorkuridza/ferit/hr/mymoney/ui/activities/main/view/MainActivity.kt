package igorkuridza.ferit.hr.mymoney.ui.activities.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.showFragment
import igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter.MainActivityPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view.AccountsFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view.BalanceFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.ViewPagerFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view.OverviewFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view.TransactionsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_app_bar.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainActivityView, NavigationView.OnNavigationItemSelectedListener {

    private val presenter: MainActivityPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
    }

    override fun initUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setSupportActionBar(toolbar)
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawerLayout,toolbar,R.string.open,R.string.close
        ){}
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        supportActionBar?.title = "Categories"
        presenter.getFirstStartOfApp()
        bottomNavigation.selectedItemId = R.id.item_categories
        showFragment(R.id.fragmentContainer, ViewPagerFragment.newInstance())
        bottomNavigation.setOnNavigationItemSelectedListener { openFragment(it) }
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
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
            R.id.item_categories -> {
                supportActionBar?.title = "Categories"
                selected = ViewPagerFragment.newInstance()
            }
            R.id.item_balance -> {
                supportActionBar?.title = "Balance"
                selected = BalanceFragment.newInstance()
            }
            R.id.item_overview -> {
                supportActionBar?.title = "Overview"
                selected = OverviewFragment.newInstance()
            }
            R.id.item_accounts -> {
                supportActionBar?.title = "Accounts"
                selected = AccountsFragment.newInstance()
            }
            R.id.item_transactions -> {
                supportActionBar?.title = "Transactions"
                selected = TransactionsFragment.newInstance()
            }
        }
        if(selected!=null) {
            showFragment(R.id.fragmentContainer, selected)
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var selected : Fragment? = null
        when(menuItem.itemId){
            R.id.accounts -> {
                bottomNavigation.selectedItemId = R.id.item_accounts
                selected = AccountsFragment.newInstance()
                supportActionBar?.title = "Accounts"
            }
            R.id.transactions -> {
                bottomNavigation.selectedItemId = R.id.item_transactions
                selected = TransactionsFragment.newInstance()
                supportActionBar?.title = "Transactions"
            }
            R.id.categories -> {
                bottomNavigation.selectedItemId = R.id.item_categories
                selected = ViewPagerFragment.newInstance()
                supportActionBar?.title = "Categories"
            }
            R.id.overview -> {
                bottomNavigation.selectedItemId = R.id.item_overview
                selected = OverviewFragment.newInstance()
                supportActionBar?.title = "Overview"
            }
            R.id.balance -> {
                bottomNavigation.selectedItemId = R.id.item_balance
                selected = BalanceFragment.newInstance()
                supportActionBar?.title = "Balance"
            }
        }
        if(selected!=null)
            showFragment(R.id.fragmentContainer, selected)
        return true
    }
}
