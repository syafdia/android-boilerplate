package com.github.syafdia.androidboilerplate.core.provider

import android.content.res.Resources

interface ResourceProvider {

    fun getString(resId: Int): String

    fun getResources(): Resources
}