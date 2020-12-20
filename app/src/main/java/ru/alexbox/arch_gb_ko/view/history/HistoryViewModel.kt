package ru.alexbox.arch_gb_ko.view.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.util.parseLocalSearchResults
import ru.alexbox.arch_gb_ko.view_model.BaseViewModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
BaseViewModel<AppState>() {

    private val liveDataForViewToObserve : LiveData<AppState> = mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
       mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, online: Boolean) {
        mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, online)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}