package com.github.syafdia.androidboilerplate.feature.dashboard.presentation

import android.databinding.ObservableField
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.DeleteAuthUserUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthSubjectUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthUserUseCase
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber


class DashboardViewModel(
        private val getAuthUserUseCase: GetAuthUserUseCase,
        private val deleteAuthUserUseCase: DeleteAuthUserUseCase,
        private val getAuthSubjectUseCase: GetAuthSubjectUseCase,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val authUserUsername = ObservableField<String>("")

    val authUserFullName = ObservableField<String>("")

    val today = ObservableField<String>(getTodayDate())

    lateinit var navigator: DashboardNavigator

    init {
        initAuthUserInfo()
        subscribeToAuthPublishSubject()
    }

    private fun initAuthUserInfo() {
        getAuthUserUseCase.execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ authUser ->
                    authUserUsername.set(authUser?.username)
                    authUserFullName.set(authUser?.fullName)
                }, { err ->
                    Timber.e(err)
                })
                .let { compositeDisposable.add(it) }
    }

    private fun subscribeToAuthPublishSubject() {
        getAuthSubjectUseCase.execute()
                .flatMap { it.getData().toObservable() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({
                    if (it.isEmpty()) {
                        navigator.openLoginActivity()
                    }
                }, { err ->
                    Timber.e(err)
                })
                .let { compositeDisposable.add(it) }
    }

    fun logout() {
        deleteAuthUserUseCase.execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({}, { err ->
                    Timber.e(err)
                })
                .let { compositeDisposable.add(it) }
    }

    private fun getTodayDate(): String {
        return DateTimeFormat.forPattern("E, d MMM Y").print(LocalDateTime.now())
    }
}