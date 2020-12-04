package ru.alexbox.arch_gb_ko.datasource

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexbox.arch_gb_ko.data.SearchResult

interface IApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<SearchResult>>
}