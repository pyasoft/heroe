package com.aac.superheroe.di

import com.aac.superheroe.data.apiheroes.HeroesService
import com.aac.superheroe.utils.AppConstants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
/*
    @Singleton
    @Provides
    fun provideHeroeResponseMapper(): HeroeResponseMapper = HeroeResponseMapper()

    @Singleton
    @Provides
    fun provideHeroesService(): HeroesService = Retrofit.Builder()
         .baseUrl(AppConstants.BASE_URL)
         .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
         .build()
         .create(HeroesService::class.java)
    */
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideHeroesService(retrofit: Retrofit): HeroesService = retrofit.create(HeroesService::class.java)

}
