package ru.alexbox.arch_gb_ko.repository

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.data.IDataSource
import ru.alexbox.arch_gb_ko.data.SearchResult

class RepositoryImplementation(private val dataSource: IDataSource<List<SearchResult>>) :
    IRepository<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        return dataSource.getData(word)
    }
}