package ru.alexbox.historyscreen

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.alexbox.core.view_model.BaseViewModel
import ru.alexbox.model.data.AppState

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

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}