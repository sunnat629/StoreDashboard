package dev.sunnat629.storedashboard.di.modules

import dagger.Module
import dagger.Provides
import dev.sunnat629.storedashboard.models.apis.ApiDataSource
import dev.sunnat629.storedashboard.models.apis.repositories.ApiRepositories
import dev.sunnat629.storedashboard.models.apis.services.ApiServices
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * ServiceModule.kt
 * This is a module class which provides all the Services during inject.
 *
 * It includes another module -
 * @see NetworkModule for more details
 * */
@Module(
    includes = [
        NetworkModule::class]
)
class ServiceModule {

    @Singleton
    @Provides
    fun bindCartDataSource(apiServices: ApiServices): ApiDataSource {
        return ApiRepositories(apiServices)
    }

    /**
     * This singleton provider provides api services which contains all the api endpoints.
     *
     * @param retrofit is an Retrofit
     * */
    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}