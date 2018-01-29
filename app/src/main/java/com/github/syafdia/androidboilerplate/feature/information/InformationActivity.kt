package com.github.syafdia.androidboilerplate.feature.information

import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import com.github.syafdia.androidboilerplate.util.ext.setContentFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_information.*
import javax.inject.Inject


class InformationActivity : BaseActivity(), HasSupportFragmentInjector {

    override val viewId = R.layout.activity_information

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_information)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentFragment(R.id.frameLayout_information_fragmentContainer, { InformationFragment() })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}