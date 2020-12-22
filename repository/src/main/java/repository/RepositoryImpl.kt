package repository

import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult


class RepositoryImpl(private val dataSource: IDataSource<List<DataModel>>) :
    IRepository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}