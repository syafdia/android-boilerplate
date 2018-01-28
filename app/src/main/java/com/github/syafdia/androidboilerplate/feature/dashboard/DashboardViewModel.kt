package com.github.syafdia.androidboilerplate.feature.dashboard

import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat


class DashboardViewModel(
        private val getAuthUserUseCase: GetAuthUserUseCase,
        private val deleteAuthUserUseCase: DeleteAuthUserUseCase,
        private val getAuthSubjectUseCase: GetAuthSubjectUseCase
) : BaseViewModel() {

    val authUserUsername = ObservableField<String>("")

    val authUserFullName = ObservableField<String>("")

    val today = ObservableField<String>(getTodayDate())

    lateinit var navigator: DashboardNavigator

    init {
        initAuthUserInfo()
        observeOnAuthUser()
    }

    fun initAuthUserInfo() {
        val authUser = getAuthUserUseCase.execute()

        authUserUsername.set(authUser?.username)
        authUserFullName.set(authUser?.fullName)
    }

    fun observeOnAuthUser() {
        compositeDisposable.add(
                getAuthSubjectUseCase.execute().subscribe { if (!it.isAuthenticated()) navigator.openLoginActivity() }
        )
    }

    fun logout() {
        deleteAuthUserUseCase.execute()
    }

    fun getTodayDate(): String {
        return DateTimeFormat.forPattern("E, d MMM Y").print(LocalDateTime.now())
    }
}