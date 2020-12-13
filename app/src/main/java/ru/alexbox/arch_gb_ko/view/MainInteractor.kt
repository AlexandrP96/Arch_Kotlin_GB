package ru.alexbox.arch_gb_ko.view

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.data.SearchResult
import ru.alexbox.arch_gb_ko.interactor.IInteractor
import ru.alexbox.arch_gb_ko.data.DataModel
import ru.alexbox.arch_gb_ko.repository.IRepository

class MainInteractor (
    private val remoteRepository: IRepository<List<SearchResult>>,
    private val localRepository: IRepository<List<SearchResult>>
) : IInteractor<DataModel> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map {
                DataModel.Success(
                    it
                )
            }
        } else {
            localRepository.getData(word).map {
                DataModel.Success(
                    it
                )
            }
        }
    }
}
