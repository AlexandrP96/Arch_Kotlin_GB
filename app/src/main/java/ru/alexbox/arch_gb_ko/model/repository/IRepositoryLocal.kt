package ru.alexbox.arch_gb_ko.model.repository

import ru.alexbox.arch_gb_ko.model.data.AppState

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun saveToDB(appState: AppState)
}