package ru.alexbox.arch_gb_ko.di

import androidx.room.Room
import org.koin.dsl.module
import repository.*
import repository.room.HistoryDataBase
import ru.alexbox.arch_gb_ko.view.main.MainInteractor
import ru.alexbox.arch_gb_ko.view.main.MainViewModel
import ru.alexbox.historyscreen.HistoryInteractor
import ru.alexbox.historyscreen.HistoryViewModel
import ru.alexbox.model.data.SearchResult

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()}
    single { get<HistoryDataBase>().historyDao() }
    single<IRepository<List<SearchResult>>> { RepositoryImpl(RetrofitImpl()) }
    single<IRepositoryLocal<List<SearchResult>>> {
        RepositoryImplLocal(
            RoomDataBaseImpl(get())
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}