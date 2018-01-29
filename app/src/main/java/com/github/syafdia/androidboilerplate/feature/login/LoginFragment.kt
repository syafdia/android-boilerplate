package com.github.syafdia.androidboilerplate.feature.login


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.Observable
import android.os.Bundle
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.databinding.FragmentLoginBinding
import com.github.syafdia.androidboilerplate.feature.BaseFragment
import com.github.syafdia.androidboilerplate.feature.dashboard.DashboardActivity
import com.github.syafdia.androidboilerplate.util.ext.showToast
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

        viewModel.generalError.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(a: Observable?, b: Int) {
                val generalErrorVal = viewModel.generalError.get()

                if (generalErrorVal.isNotEmpty()) {
                    activity?.showToast(generalErrorVal)
                    viewModel.generalError.set("")
                }
            }
        })
    }

    override fun openDashboardActivity() {
        startActivity(Intent(activity, DashboardActivity::class.java))
        activity.finish()
    }
}
