package ru.alexbox.core.view_model

interface IInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}