package ru.alexbox.arch_gb_ko.data

import io.reactivex.Observable

interface IDataSource<T> {
    fun getData(word: String): Observable<T>
}