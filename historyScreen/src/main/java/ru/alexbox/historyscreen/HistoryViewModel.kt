package ru.alexbox.historyscreen

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.alexbox.core.view_model.BaseViewModel
import ru.alexbox.model.data.DataModel

class HistoryViewModel(private val interactor: HistoryInteractor) :
BaseViewModel<DataModel>() {

    private val liveDataForViewToObserve : LiveData<DataModel> = mutableLiveData

    fun subscribe(): LiveData<DataModel> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
       mutableLiveData.value = DataModel.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, online: Boolean) {
        mutableLiveData.postValue(
            parseLocalSearchResults(
                interactor.getData(
                    word,
                    online
                )
            )
        )
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(DataModel.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = DataModel.Success(null)
        super.onCleared()
    }
}