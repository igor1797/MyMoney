package igorkuridza.ferit.hr.mymoney.ui.activities.main.presenter

interface MainActivityPresenter {

        fun onCreate()

        fun saveCategoriesAndAccountsToDb()

        fun saveFirstStartOfApp(firstStartOfApp: Boolean)

        fun getFirstStartOfApp()
}