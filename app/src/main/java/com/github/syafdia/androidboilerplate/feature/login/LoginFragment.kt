package com.github.syafdia.androidboilerplate.feature.login


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.android.databinding.library.baseAdapters.BR
import com.github.syafdia.androidboilerplate.feature.BaseFragment
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.databinding.FragmentLoginBinding
import com.github.syafdia.androidboilerplate.feature.dashboard.DashboardActivity
import timber.log.Timber
import javax.inject.Inject


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginNavigator {

    override val viewId: Int = R.layout.fragment_login

    override lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(LoginViewModel::class.java)

        viewModel.navigator = this
    }

    override fun handleError(throwable: Throwable) {
        Timber.d(throwable)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun openDashboardActivity() {
        startActivity(Intent(activity, DashboardActivity::class.java))
        activity.finish()
    }
}
