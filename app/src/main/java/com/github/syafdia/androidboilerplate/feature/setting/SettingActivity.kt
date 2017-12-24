package com.github.syafdia.androidboilerplate.feature.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SettingActivity : BaseActivity(), HasSupportFragmentInjector {

    override val viewId = R.layout.activity_setting

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewId)
        setContentFragment(R.id.frameLayout_setting_fragmentContainer, { SettingFragment() })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
