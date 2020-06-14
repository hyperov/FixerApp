package com.fixer.app.di.module

import androidx.lifecycle.ViewModelProvider
import com.fixer.app.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}