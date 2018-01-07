package com.github.syafdia.androidboilerplate.util.ext

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast


fun AppCompatActivity.setContentFragment(containerId: Int, createFragment: () -> Fragment) {
    val manager = supportFragmentManager
    val fragment = manager?.findFragmentById(containerId)

    if (fragment == null) {
        manager?.beginTransaction()
                ?.add(containerId, createFragment())
                ?.commit()
    }
}

fun Activity.showSnackbar(
        message: String,
        actionLabel: String? = null,
        action: ((view: View) -> Unit)? = null,
        duration: Int = Snackbar.LENGTH_SHORT
) {
    val parentLayout = findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(parentLayout, message.toUpperCase(), duration)

    action.let { snackbar.setAction(actionLabel, action)}

    snackbar.show()
}

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}