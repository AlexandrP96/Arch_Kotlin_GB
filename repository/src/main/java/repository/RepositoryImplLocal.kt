package repository

import ru.alexbox.model.data.AppState
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult


class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<DataModel>>) :
    IRepositoryLocal<List<DataModel>> {

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

}