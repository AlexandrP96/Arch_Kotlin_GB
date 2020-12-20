package repository

import ru.alexbox.model.data.DataModel

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun saveToDB(dataModel: DataModel)
}