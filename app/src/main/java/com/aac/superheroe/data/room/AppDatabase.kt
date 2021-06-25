package com.aac.superheroe.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aac.superheroe.domain.model.HeroeRoom

@Database(entities = arrayOf(HeroeRoom::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroeRoomDAO(): HeroeRoomDAO
}