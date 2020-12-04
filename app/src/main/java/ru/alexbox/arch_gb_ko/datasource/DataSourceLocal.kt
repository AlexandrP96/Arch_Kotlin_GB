package ru.alexbox.arch_gb_ko.datasource

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.data.IDataSource
import ru.alexbox.arch_gb_ko.data.SearchResult

class DataSourceLocal(private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()) :
    IDataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> = remoteProvider.getData(word)
}
