package ru.alexbox.arch_gb_ko.view.history

import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.model.repository.IRepositoryLocal
import ru.alexbox.arch_gb_ko.view_model.IInteractor

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