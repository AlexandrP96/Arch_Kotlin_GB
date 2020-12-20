package repository

import ru.alexbox.model.data.SearchResult


class RepositoryImpl(private val dataSource: IDataSource<List<SearchResult>>) :
    IRepository<List<SearchResult>> {

    override suspend fun getData(word: String): List<SearchResult> {
        return dataSource.getData(word)
    }
}