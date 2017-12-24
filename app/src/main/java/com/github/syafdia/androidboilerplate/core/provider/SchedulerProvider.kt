package com.github.syafdia.androidboilerplate.core.provider

import io.reactivex.Scheduler


interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}