package ru.alexbox.arch_gb_ko.di

import dagger.Module
import dagger.Provides
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun providerInteractor(
        @Named(NAME_REMOTE) repositoryRemote: IRepository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: IRepository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}