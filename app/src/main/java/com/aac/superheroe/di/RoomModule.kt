package com.aac.superheroe.di

import android.content.Context
import androidx.room.Room
import com.aac.superheroe.data.room.AppDatabase
import com.aac.superheroe.data.room.HeroeRoomDAO
import com.aac.superheroe.utils.AppConstants.BD_ROOM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BD_ROOM
        ).build()

    @Provides
    fun provideHeroeRoomDAO(db: AppDatabase): HeroeRoomDAO = db.heroeRoomDAO()

}
