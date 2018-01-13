package com.github.syafdia.androidboilerplate.feature.login

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import io.reactivex.Maybe

class LoginViewModel(
        private val auth: Auth,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider,
        private val userRepository: UserRepository
) : BaseViewModel() {

    val usernameError = ObservableField<String>()

    val passwordError = ObservableField<String>()

    val isLoading = ObservableBoolean(false)

    lateinit var navigator: LoginNavigator

    private val username = ObservableField<String>()

    private val password = ObservableField<String>()

    fun onChangeUsername(value: CharSequence?) {
        username.set(value?.toString())
        usernameError.set("")
    }

    fun onChangePassword(value: CharSequence?) {
        password.set(value?.toString())
        passwordError.set("")
    }

    fun onClickLogin() {
        val username = username.get()?.trim() ?: ""
        val password = password.get()?.trim() ?: ""

        if (username.isEmpty()) {
            usernameError.set(resourceProvider.getString(R.string.login_errorUsernameRequired))
            return
        }

        if (password.isEmpty()) {
            passwordError.set(resourceProvider.getString(R.string.login_errorPasswordRequired))
            return
        }

        isLoading.set(true)

        userRepository.authenticate(username, password)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { user ->
                            auth.setUser(user)
                            isLoading.set(false)
                            navigator.openDashboardActivity()
                        },
                        { err ->
                            isLoading.set(false)
                            navigator.handleError(err)
                        }
                )

    }
}