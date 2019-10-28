package igorkuridza.ferit.hr.mymoney

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import igorkuridza.ferit.hr.mymoney.common.PREFS_NAME
import igorkuridza.ferit.hr.mymoney.di.iteratorModule
import igorkuridza.ferit.hr.mymoney.di.presentationModule
import igorkuridza.ferit.hr.mymoney.di.repositoryModule
import igorkuridza.ferit.hr.mymoney.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMoneyApp : Application() {
    companion object {
        lateinit var instance: MyMoneyApp
            private set

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@MyMoneyApp)
            modules(listOf(iteratorModule, presentationModule, repositoryModule, splashModule))
        }
    }

    fun providePreferences(): SharedPreferences = instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}