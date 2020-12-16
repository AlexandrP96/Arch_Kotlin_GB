package ru.alexbox.arch_gb_ko.view.main

import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.view_model.IInteractor
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.repository.IRepository

class MainInteractor(
  private val repositoryRemote: IRepository<List<DataModel>>,
  private val repositoryLocal: IRepository<List<DataModel>>
) : IInteractor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if(fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
