package com.fixer.app.di.module

import com.fixer.app.view.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModules {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeMainFragmentInjector(): MainFragment
}