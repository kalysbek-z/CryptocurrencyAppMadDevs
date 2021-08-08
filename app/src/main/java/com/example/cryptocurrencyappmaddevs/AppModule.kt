package com.example.cryptocurrencyappmaddevs

import android.app.Application
import androidx.room.Room
import com.example.cryptocurrencyappmaddevs.api.ServiceAPI
import com.example.cryptocurrencyappmaddevs.data.CurrencyDatabase
import com.example.cryptocurrencyappmaddevs.utils.ConstValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ConstValues.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ServiceAPI =
        retrofit.create(ServiceAPI::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CurrencyDatabase =
        Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_database")
            .build()
}