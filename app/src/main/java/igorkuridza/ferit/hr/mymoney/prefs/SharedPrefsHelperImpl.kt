package igorkuridza.ferit.hr.mymoney.prefs

import igorkuridza.ferit.hr.mymoney.MyMoneyApp
import igorkuridza.ferit.hr.mymoney.common.PREFS_KEY

class SharedPrefsHelperImpl(): SharedPrefsHelper {

    private val preferences = MyMoneyApp.instance.providePreferences()

    override fun storeFirstStartOfApp(firstStartOfApp: Boolean) {
        preferences.edit().putBoolean(PREFS_KEY,firstStartOfApp).apply()
    }

    override fun getFirstStartOfApp(): Boolean {
        return preferences.getBoolean(PREFS_KEY, true)
    }
}

fun provideSharedPrefs(): SharedPrefsHelper {
    return SharedPrefsHelperImpl()
}