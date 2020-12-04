package ru.alexbox.arch_gb_ko.datasource

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.data.IDataSource
import ru.alexbox.arch_gb_ko.data.SearchResult

// Заглушка
class RoomDataBaseImpl : IDataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}