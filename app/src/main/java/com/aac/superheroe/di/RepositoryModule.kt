package com.aac.superheroe.di

import com.aac.superheroe.data.apiheroes.HeroesService
import com.aac.superheroe.data.room.HeroeRoomDAO
import com.aac.superheroe.domain.repository.HeroeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
/*
    @ViewModelScoped
    @Provides
    fun provideHeroeRepository(
        heroesService: HeroesService,
        heroeFBDAO: HeroeFBDAO,
        heroeRoomDAO: HeroeRoomDAO
    ): HeroeRepository {
        return HeroeRepository_Impl(
            heroesService,
            heroeFBDAO,
            heroeRoomDAO
        )
    }
*/
    @ViewModelScoped
    @Provides
    fun provideHeroeRepository(
        heroesService: HeroesService,
        heroeRoomDAO: HeroeRoomDAO
    ): HeroeRepository {
        return HeroeRepository(
            heroesService,
            heroeRoomDAO
        )
    }

}
