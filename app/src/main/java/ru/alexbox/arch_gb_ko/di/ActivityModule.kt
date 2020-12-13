package ru.alexbox.arch_gb_ko.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.alexbox.arch_gb_ko.view.main.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}