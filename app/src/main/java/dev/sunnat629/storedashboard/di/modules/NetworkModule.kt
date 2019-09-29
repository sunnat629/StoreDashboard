package dev.sunnat629.storedashboard.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dev.sunnat629.storedashboard.models.networks.RetrofitFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * NetworkModule.kt
 * This is the code module and it contains the network related providers.
 * */
@Module
class NetworkModule {

    /**
     * This singleton provider provides an OkHttpClient Builder
     * */
    @Provides
    @Singleton
    fun provideClientBuilder(): OkHttpClient.Builder {
        return RetrofitFactory.createClientBuilder()
    }

    /**
     * This singleton provider provides an unauthorized OkHttpClient
     *
     * @param clientBuilder is an {@code @UnAuthorized} OkHttpClient Builder
     * */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        clientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return clientBuilder
            .build()
    }

    /**
     * This singleton provider provides an unauthorized Retrofit
     *
     * @param okHttpClient is an {@code @UnAuthorized} OkHttpClient
     * */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.createRetrofit(okHttpClient, CoroutineCallAdapterFactory())
    }
}