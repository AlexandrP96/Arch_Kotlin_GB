package repository

import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult


class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<SearchResult>>) :
    IRepositoryLocal<List<SearchResult>> {

    override suspend fun saveToDB(dataModel: DataModel) {
        dataSource.saveToDB(dataModel)
    }

    override suspend fun getData(word: String): List<SearchResult> {
        return dataSource.getData(word)
    }

}