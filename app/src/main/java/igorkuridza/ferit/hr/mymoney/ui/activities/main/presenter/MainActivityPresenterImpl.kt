package igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter


import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.CategoryColor
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRepository
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRepository
import igorkuridza.ferit.hr.mymoney.prefs.SharedPrefsHelper
import igorkuridza.ferit.hr.mymoney.ui.activities.main.view.MainActivityView

class MainActivityPresenterImpl(
    private val view: MainActivityView,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository,
    private val prefs: SharedPrefsHelper): MainActivityPresenter {

    override fun onCreate() {
        view.initUi()
    }

    override fun saveCategoriesAndAccountsToDb() {
        categoryRepository.apply {
            addCategory(
                Category(
                    name = "Hrana i piće",
                    color = CategoryColor.FIRST,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Režije stanovanja",
                    color = CategoryColor.SECOND,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Prijevoz",
                    color = CategoryColor.THIRD,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Odjeća i obuća",
                    color = CategoryColor.FOURTH,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Obrazovanje",
                    color = CategoryColor.FIFTH,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Zabava",
                    color = CategoryColor.SIXTH,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Putovanja",
                    color = CategoryColor.SEVENTH,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Zdravlje",
                    color = CategoryColor.EIGHT,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )
            addCategory(
                Category(
                    name = "Ostalo",
                    color = CategoryColor.NINTH,
                    typeOfCategory = TypeOfCategory.EXPENSES
                )
            )

            addCategory(
                Category(
                    name = "Plaća",
                    color = CategoryColor.NINTH,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Nagrade i bonusi",
                    color = CategoryColor.EIGHT,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Dohodak od imovine",
                    color = CategoryColor.SEVENTH,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Dohodak od nesamostalnog rada",
                    color = CategoryColor.SIXTH,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Dohodak od samostalne djelatnosti",
                    color = CategoryColor.FIFTH,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Mirovine",
                    color = CategoryColor.FOURTH,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Naknade za nezaposlenost",
                    color = CategoryColor.THIRD,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Primljeni pokloni",
                    color = CategoryColor.SECOND,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
            addCategory(
                Category(
                    name = "Ostala tekuća primanja",
                    color = CategoryColor.FIRST,
                    typeOfCategory = TypeOfCategory.INCOMES
                )
            )
        }

        accountRepository.apply {
            addAccount(
                Account(
                    name = "Kartica",
                    amountOfMoney = 0F,
                    color = R.color.accountCardColor
                )
            )
            addAccount(
                Account(
                    name = "Gotovina",
                    amountOfMoney = 0F,
                    color = R.color.accountCashColor
                )
            )
        }
    }

    override fun saveFirstStartOfApp(firstStartOfApp: Boolean) {
        prefs.storeFirstStartOfApp(firstStartOfApp)
    }

    override fun getFirstStartOfApp() {
        view.onGetFirstStartOfApp(prefs.getFirstStartOfApp())
    }
}