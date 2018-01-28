package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth
import io.reactivex.subjects.PublishSubject

class GetAuthSubjectUseCase(private val auth: Auth) {

    fun execute(): PublishSubject<Auth> {
        return auth.subject
    }
}