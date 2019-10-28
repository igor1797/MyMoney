package igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.presenter

import igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.view.SplashView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit

class SplashPresenterImpl(private val view: SplashView): SplashPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        startTimer()
    }

    override fun ondDestroy() {
        if(!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    private fun startTimer(){
        val timerDisposable = Observable
            .timer(1500, TimeUnit.MICROSECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.goToMainActivity()}, { it.printStackTrace() })
        compositeDisposable.add(timerDisposable)
    }
}