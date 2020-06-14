package com.fixer.app.di.component

import android.content.Context
import com.fixer.app.App
import com.fixer.app.di.module.FragmentsModules
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentsModules::class]
)
interface MyApplicationComponent : AndroidInjector<App> {

    override fun inject(instance: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): MyApplicationComponent
    }
}