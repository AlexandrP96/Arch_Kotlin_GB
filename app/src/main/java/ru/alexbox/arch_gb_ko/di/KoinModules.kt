package ru.alexbox.arch_gb_ko.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data_source.RetrofitImpl
import ru.alexbox.arch_gb_ko.model.data_source.RoomDataBaseImpl
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.model.repository.RepositoryImpl

val application = module {
    single<IRepository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }
    single<IRepository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImpl(
        RoomDataBaseImpl()
    ) }
}

val mainScreen = module {

}