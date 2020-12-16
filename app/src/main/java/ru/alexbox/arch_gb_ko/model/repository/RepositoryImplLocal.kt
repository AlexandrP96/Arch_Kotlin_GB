package ru.alexbox.arch_gb_ko.model.repository

import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data_source.IDataSourceLocal

class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<DataModel>>) :
IRepositoryLocal<List<DataModel>>{

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

}