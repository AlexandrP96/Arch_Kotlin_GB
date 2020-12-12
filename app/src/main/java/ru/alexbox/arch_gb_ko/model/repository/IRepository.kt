package ru.alexbox.arch_gb_ko.model.repository

interface IRepository<T> {
    suspend fun getData(word: String): T
}