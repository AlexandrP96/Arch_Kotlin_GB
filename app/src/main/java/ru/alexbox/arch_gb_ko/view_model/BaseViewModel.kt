package ru.alexbox.arch_gb_ko.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.alexbox.arch_gb_ko.model.data.AppState

abstract class BaseViewModel<T : AppState>(
    protected open val mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
    abstract fun getData(word: String, isOnline: Boolean)
    abstract fun handleError(error: Throwable)
}
