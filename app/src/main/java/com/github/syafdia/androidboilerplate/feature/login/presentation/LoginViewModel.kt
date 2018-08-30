package com.github.syafdia.androidboilerplate.feature.login.presentation

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import com.github.syafdia.androidboilerplate.feature.login.domain.usecase.AuthenticateUserUseCase
import com.github.syafdia.androidboilerplate.feature.login.domain.usecase.SaveAuthUserUseCase
import timber.log.Timber

class LoginViewModel(
        private val authenticateUserUseCase: AuthenticateUserUseCase,
        private val saveAuthUserUseCase: SaveAuthUserUseCase,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val usernameError = ObservableField<String>("")

    val passwordError = ObservableField<String>("")

    val isLoading = ObservableBoolean(false)

    val generalError = ObservableField<String>("")

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

        authenticateUserUseCase.execute(username, password)
                .flatMap { saveAuthUserUseCase.execute(it) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({
                    isLoading.set(false)
                    navigator.openDashboardActivity()
                }, { err ->
                    isLoading.set(false)
                    generalError.set(err.message)

                    Timber.e(err)
                }).let { compositeDisposable.add(it) }

    }
}