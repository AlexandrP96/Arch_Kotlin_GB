package ru.alexbox.arch_gb_ko.model.data_source

import ru.alexbox.arch_gb_ko.model.data.DataModel

class RoomDataBaseImpl : IDataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}