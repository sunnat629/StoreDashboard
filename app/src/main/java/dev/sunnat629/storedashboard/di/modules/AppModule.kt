package dev.sunnat629.storedashboard.di.modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.sunnat629.storedashboard.di.qualifires.ApplicationContext
import dev.sunnat629.storedashboard.ui.activities.ContentActivity
import dev.sunnat629.storedashboard.ui.activities.MainActivity
import dev.sunnat629.storedashboard.ui.fragments.*
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import dev.sunnat629.storedashboard.viewmodels.ViewModelFactory
import kotlin.reflect.KClass


@Module
abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract fun provideApplicationContext(application: Application): Context


    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindNoticeFragment(): NoticeFragment

    @ContributesAndroidInjector
    abstract fun bindProgressFragment(): ProgressFragment

    @ContributesAndroidInjector
    abstract fun bindAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun bindStockFragment(): StockFragment

    @ContributesAndroidInjector
    abstract fun bindBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindContentActivity(): ContentActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)