package ru.alexbox.arch_gb_ko.view_model

interface IInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}