package com.github.syafdia.androidboilerplate.feature.login

import android.os.Bundle
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setContentFragment(R.id.frameLayout_login_fragmentContainer, { LoginFragment() })
    }

}
