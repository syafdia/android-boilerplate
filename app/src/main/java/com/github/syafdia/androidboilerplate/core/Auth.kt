package com.github.syafdia.androidboilerplate.core

import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass


class Auth(private val storage: Storage) {

    val subject: PublishSubject<Auth> = PublishSubject.create()

    fun setData(data: String): Single<Unit> {
        return storage.putAsync(AUTH_DATA, data).map { subject.onNext(this) }
    }

    fun getData(): Single<String> {
        return storage.getAsync(AUTH_DATA)
    }

    fun <T: Any> getDataAs(kClazz: KClass<T>): Single<T> {
        return getData().map { Json.parseAs(kClazz, it) }
    }

    fun deleteData(): Single<Unit> {
        return storage.deleteAsync(AUTH_DATA).map { subject.onNext(this) }
    }

    companion object {
        private const val AUTH_DATA = "AUTH_DATA"
    }
}