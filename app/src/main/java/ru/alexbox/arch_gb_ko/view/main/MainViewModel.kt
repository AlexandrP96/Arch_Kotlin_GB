package ru.alexbox.arch_gb_ko.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexbox.arch_gb_ko.utils.parseOnlineSearchResults
import ru.alexbox.core.view_model.BaseViewModel
import ru.alexbox.model.data.DataModel

class MainViewModel(private val interactor: MainInteractor) :
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

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(DataModel.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = DataModel.Success(null)
        super.onCleared()
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
    }

}
