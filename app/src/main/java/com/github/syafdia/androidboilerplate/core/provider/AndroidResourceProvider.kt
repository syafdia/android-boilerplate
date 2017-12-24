package com.github.syafdia.androidboilerplate.core.provider

import android.content.Context
import android.content.res.Resources

class AndroidResourceProvider(private val context: Context): ResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }


    override fun getResources(): Resources {
        return context.resources
    }
}