package com.github.syafdia.androidboilerplate.core.provider

import io.reactivex.Scheduler


interface SchedulerProvider {

    fun main(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}