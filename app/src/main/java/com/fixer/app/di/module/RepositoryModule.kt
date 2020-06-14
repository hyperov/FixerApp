package com.fixer.app.di.module

import com.fixer.app.model.Repository
import com.fixer.app.model.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideTasksMainDataSource(repo: RepositoryImpl): Repository
}