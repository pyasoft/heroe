package com.aac.superheroe.data.room

import androidx.room.*
import com.aac.superheroe.domain.model.HeroeRoom
import com.aac.superheroe.utils.AppConstants.BD_ROOM

@Dao
interface HeroeRoomDAO {

    @Query("SELECT * FROM " + BD_ROOM + " ORDER BY name")
    fun getAll(): List<HeroeRoom>


    @Query("SELECT * FROM " + BD_ROOM + " WHERE publisher LIKE :company ORDER BY name")
    fun getCompany(company: String): List<HeroeRoom>


    @Query("SELECT * FROM " + BD_ROOM + " WHERE id = :id LIMIT 1")
    fun findById(id: Int): HeroeRoom


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(heroes: List<HeroeRoom>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg heroe: HeroeRoom)

    @Query("DELETE FROM " + BD_ROOM)
    fun deleteAll()

    @Delete
    fun delete(vararg heroe: HeroeRoom)

    @Update
    fun update(vararg heroe: HeroeRoom)

}