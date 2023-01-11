package com.mohamedmabrouk.estarta_task.di

import android.content.Context
import com.mohamedmabrouk.estarta_task.presentation.App
import com.mohamedmabrouk.estarta_task.source.DefaultProductsRepository
import com.mohamedmabrouk.estarta_task.source.ProductsRepository
import com.mohamedmabrouk.estarta_task.source.remote.ProductAPIs
import com.mohamedmabrouk.estarta_task.source.remote.ProductsRemoteDataSource
import com.mohamedmabrouk.estarta_task.utils.Constants
import com.mohamedmabrouk.estarta_task.utils.NetworkState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ProductAPIs {
        return retrofit.create(ProductAPIs::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkState(context: Context): NetworkState {
        return NetworkState(context)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        networkState: NetworkState,
        productAPIs: ProductAPIs
    ): ProductsRepository {
        return DefaultProductsRepository(
            ProductsRemoteDataSource(
                networkState,
                productAPIs
            )
        )
    }
}