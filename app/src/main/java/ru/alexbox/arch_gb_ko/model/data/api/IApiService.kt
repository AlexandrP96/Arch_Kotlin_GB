package ru.alexbox.arch_gb_ko.model.data.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexbox.arch_gb_ko.model.data.DataModel

interface IApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}