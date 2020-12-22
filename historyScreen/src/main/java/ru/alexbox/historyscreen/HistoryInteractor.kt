package ru.alexbox.historyscreen

import repository.IRepository
import repository.IRepositoryLocal
import ru.alexbox.core.view_model.IInteractor
import ru.alexbox.model.data.AppState
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult

class HistoryInteractor(
    private val repositoryRemote : IRepository<List<DataModel>>,
    private val repositoryLocal : IRepositoryLocal<List<DataModel>>
) : IInteractor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }


}