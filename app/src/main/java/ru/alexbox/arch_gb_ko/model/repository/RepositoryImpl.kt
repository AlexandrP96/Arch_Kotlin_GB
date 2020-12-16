package ru.alexbox.arch_gb_ko.model.repository

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data_source.IDataSource

class RepositoryImpl(private val dataSource: IDataSource<List<DataModel>>) :
    IRepository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}