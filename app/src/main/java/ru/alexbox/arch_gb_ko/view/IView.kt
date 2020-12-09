package ru.alexbox.arch_gb_ko.view

import ru.alexbox.arch_gb_ko.model.data.DataModel

interface IView {
    fun renderData(appstate: DataModel)
}