package igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.ui.activities.main.view.MainActivity
import igorkuridza.ferit.hr.mymoney.ui.activities.splash_screen.presenter.SplashPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashView {

    private val splashPresenter: SplashPresenter by inject {parametersOf(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        splashPresenter.ondDestroy()
    }

    override fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
