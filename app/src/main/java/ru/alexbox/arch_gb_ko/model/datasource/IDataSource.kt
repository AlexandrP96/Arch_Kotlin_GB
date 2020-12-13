package ru.alexbox.arch_gb_ko.model.datasource

import io.reactivex.Observable

interface IDataSource<T> {
    fun getData(word: String): Observable<T>
}