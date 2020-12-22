package repository

import repository.room.IHistoryDao
import ru.alexbox.model.data.AppState
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult


class RoomDataBaseImpl(private val historyDao: IHistoryDao) :
IDataSourceLocal<List<DataModel>>{

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

}