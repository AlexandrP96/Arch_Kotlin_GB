package ru.alexbox.historyscreen

import repository.IRepository
import repository.IRepositoryLocal
import ru.alexbox.core.view_model.IInteractor
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult

class HistoryInteractor(
    private val repositoryRemote : IRepository<List<SearchResult>>,
    private val repositoryLocal : IRepositoryLocal<List<SearchResult>>
) : IInteractor<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return DataModel.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }


}