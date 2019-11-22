package com.rifafauzi.footballmatch.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.rifafauzi.footballmatch.FootballApp
import com.rifafauzi.footballmatch.di.module.*
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: FootballApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: FootballApp)
}