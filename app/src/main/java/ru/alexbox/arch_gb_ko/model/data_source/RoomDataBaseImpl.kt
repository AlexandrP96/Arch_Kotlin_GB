package ru.alexbox.arch_gb_ko.model.data_source

import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.room.IHistoryDao
import ru.alexbox.arch_gb_ko.util.convertDataModelSuccessToEntity
import ru.alexbox.arch_gb_ko.util.mapHistoryEntityToSearchResult

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