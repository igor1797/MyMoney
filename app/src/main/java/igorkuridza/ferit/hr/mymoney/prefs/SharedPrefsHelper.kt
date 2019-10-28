package igorkuridza.ferit.hr.mymoney.prefs

interface SharedPrefsHelper {

    fun storeFirstStartOfApp(firstStartOfApp: Boolean)

    fun getFirstStartOfApp(): Boolean
}