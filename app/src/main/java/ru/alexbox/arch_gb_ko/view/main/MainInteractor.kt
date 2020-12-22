package ru.alexbox.arch_gb_ko.view.main

import repository.IRepository
import repository.IRepositoryLocal
import ru.alexbox.core.view_model.IInteractor
import ru.alexbox.model.data.AppState
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult

class MainInteractor(
    private val repositoryRemote: IRepository<List<DataModel>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
