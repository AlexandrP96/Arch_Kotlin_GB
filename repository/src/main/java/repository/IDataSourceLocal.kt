package repository

import ru.alexbox.model.data.AppState

interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun saveToDB(appState : AppState)
}