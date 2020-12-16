package ru.alexbox.arch_gb_ko.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.alexbox.arch_gb_ko.di.application
import ru.alexbox.arch_gb_ko.di.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
           modules(listOf(application, mainScreen))
       }
    }

}