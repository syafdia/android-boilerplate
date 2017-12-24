package com.github.syafdia.androidboilerplate.feature.setting


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.android.databinding.library.baseAdapters.BR
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseFragment
import com.github.syafdia.androidboilerplate.databinding.FragmentSettingBinding
import javax.inject.Inject


class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(), SettingNavigator {

    override val viewId: Int = R.layout.fragment_setting

    override lateinit var viewModel: SettingViewModel

    @Inject
    lateinit var viewModelFactory: SettingViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(SettingViewModel::class.java)

        viewModel.navigator = this
    }
}
