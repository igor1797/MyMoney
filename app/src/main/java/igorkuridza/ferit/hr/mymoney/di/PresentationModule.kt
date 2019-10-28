package igorkuridza.ferit.hr.mymoney.di

import igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter.MainActivityPresenter
import igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter.MainActivityPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.activities.main.view.MainActivityView
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.presenter.AccountsFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.presenter.AccountsFragmentPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.accounts.view.AccountsFragmentView
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.presenter.AddTransactionFragmentDialogPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.presenter.AddTransactionFragmentDialogPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.view.AddTransactionFragmentDialogView
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.presenter.BalanceFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.presenter.BalanceFragmentPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.balance.view.BalanceFragmentView
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.presenter.CategoriesFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.presenter.CategoriesFragmentPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.view.CategoriesFragmentView
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.presenter.ChangeAccountFragmentDialogPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.presenter.ChangeAccountFragmentDialogPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.view.ChangeAccountFragmentDialogView
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.presenter.OverviewFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.presenter.OverviewFragmentPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.overview.view.OverviewFragmentView
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter.TransactionsFragmentPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.presenter.TransactionsFragmentPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.fragments.transactions.view.TransactionsFragmentView
import org.koin.dsl.module

val presentationModule = module {
    factory<CategoriesFragmentPresenter> { (view: CategoriesFragmentView) ->
        CategoriesFragmentPresenterImpl(view, get(), get())
    }
    factory<OverviewFragmentPresenter> { (view: OverviewFragmentView) ->
        OverviewFragmentPresenterImpl(view, get(), get())
    }
    factory<BalanceFragmentPresenter> { (view: BalanceFragmentView) ->
        BalanceFragmentPresenterImpl(view, get(), get(), get(), get())
    }
    factory<AddTransactionFragmentDialogPresenter> { (view: AddTransactionFragmentDialogView) ->
        AddTransactionFragmentDialogPresenterImpl(view, get(), get(), get(), get())
    }
    factory<AccountsFragmentPresenter> { (view: AccountsFragmentView) ->
        AccountsFragmentPresenterImpl(view, get())
    }
    factory<MainActivityPresenter> { (view: MainActivityView) ->
        MainActivityPresenterImpl(view, get(), get(), get())
    }
    factory<TransactionsFragmentPresenter> { (view: TransactionsFragmentView) ->
        TransactionsFragmentPresenterImpl(view, get())
    }
    factory<ChangeAccountFragmentDialogPresenter> { (view: ChangeAccountFragmentDialogView) ->
        ChangeAccountFragmentDialogPresenterImpl(view, get())
    }
}