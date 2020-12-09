package ru.alexbox.arch_gb_ko.presenter

import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.view.IView

interface IPresenter<T : DataModel, V : IView> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}