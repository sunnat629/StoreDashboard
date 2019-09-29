package dev.sunnat629.storedashboard.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.sunnat629.storedashboard.RootApplication
import dev.sunnat629.storedashboard.di.modules.AppModule
import dev.sunnat629.storedashboard.di.modules.NetworkModule
import dev.sunnat629.storedashboard.di.modules.ServiceModule
import javax.inject.Singleton

/**
 * AppComponent.kt
 * The singleton component interface is responsible for providing application instances.
 *
 * It includes some modules -
 * @see AndroidInjectionModule for more details
 * @see NetworkModule for more details
 * @see ServiceModule for more details
 * @see AppModule for more details

 * */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ServiceModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    /**
     * Here, it binds some instance to Component. In this case I created an interface with
     * {@linkplain @Component.Builder annotation} and add whatever function I want to add to builder.
     * In this case, Application is add to the AppComponent.
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: RootApplication)
}