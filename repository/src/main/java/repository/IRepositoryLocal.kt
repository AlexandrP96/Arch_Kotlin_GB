package repository

import ru.alexbox.model.data.AppState


interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun saveToDB(appState: AppState)
}