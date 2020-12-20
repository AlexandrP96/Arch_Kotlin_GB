package ru.alexbox.arch_gb_ko.model.data_source

import ru.alexbox.arch_gb_ko.model.data.AppState


interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun saveToDB(appState: AppState)
}