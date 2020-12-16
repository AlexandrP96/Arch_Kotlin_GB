package ru.alexbox.arch_gb_ko.model.data_source

interface IDataSource<T> {
    suspend fun getData(word: String): T
}