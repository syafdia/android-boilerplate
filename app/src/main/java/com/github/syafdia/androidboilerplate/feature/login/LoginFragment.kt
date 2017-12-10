package com.github.syafdia.androidboilerplate.feature.login


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class LoginFragment : BaseFragment() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        loginViewModel.startFoo()

        return inflater!!.inflate(R.layout.fragment_login, container, false)
    }
}
