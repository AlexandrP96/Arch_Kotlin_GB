package ru.alexbox.arch_gb_ko.model.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.model.data.SearchResult

interface IApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}