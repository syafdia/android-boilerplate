package com.github.syafdia.androidboilerplate.feature.dashboard

import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber


class DashboardViewModel(
        private val auth: Auth,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider,
        private val userRepository: UserRepository
) : BaseViewModel() {

    val authUserUsername = ObservableField<String>("")

    val authUserFullName = ObservableField<String>("")

    val totalCreated = ObservableField<String>("0")

    val totalOnProcess = ObservableField<String>("0")

    val today = ObservableField<String>(getTodayDate())

    lateinit var navigator: DashboardNavigator

    init {
        compositeDisposable.addAll(
            auth.userSubject
                    .subscribe { maybeAuthUser -> run {
                        val authUser = maybeAuthUser.blockingGet()

                        if (authUser == null) {
                            userRepository
                                    .deleteAuthenticated()
                                    .subscribeOn(schedulerProvider.io())
                                    .observeOn(schedulerProvider.ui())
                                    .subscribe { _ -> navigator.toLoginActivity()}
                        } else {
                            val user = authUser as User

                            authUserUsername.set(user.username)
                            authUserFullName.set(user.fullName)
                        }
                    }}
        )
    }

    fun logout() {
        auth.logOut()
    }

    private fun getTodayDate(): String {
        return DateTimeFormat.forPattern("E, d MMM Y").print(LocalDateTime.now())
    }
}