package ru.alexbox.arch_gb_ko.view.main

import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.view_model.IInteractor
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.model.repository.IRepositoryLocal

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
