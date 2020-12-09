package ru.alexbox.arch_gb_ko.di

import dagger.Module
import dagger.Provides
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.datasource.IDataSource
import ru.alexbox.arch_gb_ko.model.datasource.RetrofitImpl
import ru.alexbox.arch_gb_ko.model.datasource.RoomDataBaseImpl
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) data_source_remote: IDataSource<List<DataModel>>)
    : IRepository<List<DataModel>> = RepositoryImplementation(data_source_remote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) data_source_local: IDataSource<List<DataModel>>)
    : IRepository<List<DataModel>> = RepositoryImplementation(data_source_local)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): IDataSource<List<DataModel>> =
        RetrofitImpl()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): IDataSource<List<DataModel>> = RoomDataBaseImpl()

}