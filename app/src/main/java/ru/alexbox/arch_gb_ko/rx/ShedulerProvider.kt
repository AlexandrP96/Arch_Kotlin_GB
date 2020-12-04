package ru.alexbox.arch_gb_ko.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ISchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}

class ShedulerProvider : ISchedulerProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
}



