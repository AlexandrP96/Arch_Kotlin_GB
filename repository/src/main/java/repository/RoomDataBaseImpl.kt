package repository

import repository.room.IHistoryDao
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult


class RoomDataBaseImpl(private val historyDao: IHistoryDao) :
IDataSourceLocal<List<SearchResult>>{

    override suspend fun saveToDB(dataModel: DataModel) {
        convertDataModelSuccessToEntity(dataModel)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<SearchResult> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

}