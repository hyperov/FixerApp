package com.fixer.app.di.module

import com.fixer.app.model.Repository
import com.fixer.app.model.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
class RepositoryModule {

    @Binds
    fun provideTasksMainDataSource(repo: RepositoryImpl): Repository {
        return repo
    }
}