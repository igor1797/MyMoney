package igorkuridza.ferit.hr.mymoney.di

import igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.presenter.SplashPresenter
import igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.presenter.SplashPresenterImpl
import igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.view.SplashView
import org.koin.dsl.module

val splashModule = module {
    factory<SplashPresenter> { (view: SplashView) -> SplashPresenterImpl(view) }
}