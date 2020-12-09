package ru.alexbox.arch_gb_ko.view.main

import io.reactivex.Observable
import ru.alexbox.arch_gb_ko.di.NAME_LOCAL
import ru.alexbox.arch_gb_ko.di.NAME_REMOTE
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.view_model.IInteractor
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: IRepository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: IRepository<List<DataModel>>
) : IInteractor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }

}
