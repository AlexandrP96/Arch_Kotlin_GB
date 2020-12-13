package ru.alexbox.arch_gb_ko.model.datasource

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data.SearchResult

// Заглушка
class RoomDataBaseImpl : IDataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}