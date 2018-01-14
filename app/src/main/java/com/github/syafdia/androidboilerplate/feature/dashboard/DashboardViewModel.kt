package com.github.syafdia.androidboilerplate.feature.dashboard

import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat


class DashboardViewModel(private val auth: Auth) : BaseViewModel() {

    val authUserUsername = ObservableField<String>("")

    val authUserFullName = ObservableField<String>("")

    val today = ObservableField<String>(getTodayDate())

    lateinit var navigator: DashboardNavigator

    init {
        val authUser = auth.getUser()

        authUserUsername.set(authUser?.username)
        authUserFullName.set(authUser?.fullName)

        compositeDisposable.add(
                auth.subject.subscribe { if (!it.isAuthenticated()) navigator.openLoginActivity() }
        )
    }

    fun logout() {
        auth.deleteUser()
    }

    private fun getTodayDate(): String {
        return DateTimeFormat.forPattern("E, d MMM Y").print(LocalDateTime.now())
    }
}