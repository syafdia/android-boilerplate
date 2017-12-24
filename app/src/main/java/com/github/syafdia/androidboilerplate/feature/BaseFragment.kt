package com.github.syafdia.androidboilerplate.feature

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T: ViewDataBinding, out U: BaseViewModel> : Fragment() {

    abstract val viewId: Int

    abstract val viewModel: U

    lateinit var viewDataBinding: T

    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, viewId, container, false)
        rootView = viewDataBinding.root

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.executePendingBindings()
    }
}