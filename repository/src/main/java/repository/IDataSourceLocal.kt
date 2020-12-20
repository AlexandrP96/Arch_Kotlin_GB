package repository

import ru.alexbox.model.data.DataModel

interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun saveToDB(dataModel: DataModel)
}