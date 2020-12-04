package ru.alexbox.arch_gb_ko.repository

import io.reactivex.Observable

interface IRepository<T> {
    fun getData(word: String): Observable<T>
}