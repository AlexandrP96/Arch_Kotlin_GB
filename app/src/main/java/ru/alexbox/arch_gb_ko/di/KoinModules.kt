package ru.alexbox.arch_gb_ko.di

import androidx.room.Room
import org.koin.dsl.module
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data_source.RetrofitImpl
import ru.alexbox.arch_gb_ko.model.data_source.RoomDataBaseImpl
import ru.alexbox.arch_gb_ko.model.repository.IRepository
import ru.alexbox.arch_gb_ko.model.repository.IRepositoryLocal
import ru.alexbox.arch_gb_ko.model.repository.RepositoryImpl
import ru.alexbox.arch_gb_ko.model.repository.RepositoryImplLocal
import ru.alexbox.arch_gb_ko.room.HistoryDataBase
import ru.alexbox.arch_gb_ko.view.history.HistoryInteractor
import ru.alexbox.arch_gb_ko.view.history.HistoryViewModel
import ru.alexbox.arch_gb_ko.view.main.MainInteractor
import ru.alexbox.arch_gb_ko.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()}
    single { get<HistoryDataBase>().historyDao() }
    single<IRepository<List<DataModel>>> {RepositoryImpl(RetrofitImpl())}
    single<IRepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}