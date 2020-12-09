package ru.alexbox.arch_gb_ko.model.repository

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.datasource.IDataSource

class RepositoryImplementation(private val dataSource: IDataSource<List<DataModel>>) :
    IRepository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}