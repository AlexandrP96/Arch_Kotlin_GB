package ru.alexbox.arch_gb_ko.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.rx.ShedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected open val liveDataForViewObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val shedulerProvider: ShedulerProvider = ShedulerProvider()
) : ViewModel() {
    abstract fun getData(word: String, isOnline: Boolean)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
