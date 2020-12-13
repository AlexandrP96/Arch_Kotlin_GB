package ru.alexbox.arch_gb_ko.view_model

import io.reactivex.Observable

interface IInteractor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}