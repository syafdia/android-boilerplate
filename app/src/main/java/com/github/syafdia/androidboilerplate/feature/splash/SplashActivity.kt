package com.github.syafdia.androidboilerplate.feature.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import com.github.syafdia.androidboilerplate.feature.login.LoginActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    private val SPLASH_TIMEOUT: Long = 1_000

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, SPLASH_TIMEOUT)
    }
}
