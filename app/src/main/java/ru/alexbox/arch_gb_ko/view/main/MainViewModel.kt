package ru.alexbox.arch_gb_ko.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexbox.arch_gb_ko.utils.parseOnlineSearchResults
import ru.alexbox.core.view_model.BaseViewModel
import ru.alexbox.model.data.AppState
import ru.alexbox.model.data.DataModel

class MainViewModel(private val interactor: MainInteractor) :
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

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
    }
}
