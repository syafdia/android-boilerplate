package com.github.syafdia.androidboilerplate.feature.login

import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



class LoginActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setContentFragment(R.id.frameLayout_login_fragmentContainer, { LoginFragment() })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
