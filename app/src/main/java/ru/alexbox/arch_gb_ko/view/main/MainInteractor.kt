package ru.alexbox.arch_gb_ko.view.main

import repository.IRepository
import repository.IRepositoryLocal
import ru.alexbox.arch_gb_ko.AppState
import ru.alexbox.core.view_model.IInteractor
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult

class MainInteractor(
    private val repositoryRemote: IRepository<List<SearchResult>>,
    private val repositoryLocal: IRepositoryLocal<List<SearchResult>>
) : IInteractor<DataModel> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        val dataModel : DataModel
        if (fromRemoteSource) {
            dataModel = DataModel.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(dataModel)
        } else {
            dataModel = DataModel.Success(repositoryLocal.getData(word))
        }
        return dataModel
    }
}
