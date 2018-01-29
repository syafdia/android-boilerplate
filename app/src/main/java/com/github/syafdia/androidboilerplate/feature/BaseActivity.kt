package com.github.syafdia.androidboilerplate.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection


abstract class BaseActivity : AppCompatActivity() {

    abstract val viewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(viewId)
    }
}