package com.aac.superheroe.domain.repository

import android.util.Log
import com.aac.superheroe.data.apiheroes.HeroesService
import com.aac.superheroe.data.room.HeroeRoomDAO
import com.aac.superheroe.domain.model.*
import javax.inject.Inject


//@Singleton
class HeroeRepository @Inject constructor(
    private val serviceResponse: HeroesService,
    private val serviceRoom: HeroeRoomDAO
) {

    //PART OF API HEROES
    suspend fun getAll_API(): List<HeroeComun> {
        val mapperResponse = HeroeResponseMapper()
        return mapperResponse.toDomainList(serviceResponse.getAll())
    }


    //PART OF ROOM
    fun getAll_Room(): List<HeroeComun> {
        val mapperRoom = HeroeRoomMapper()
        return mapperRoom.toDomainList(serviceRoom.getAll())
    }

    fun getCompany_Room(company: String): List<HeroeComun> {
        val mapperRoom = HeroeRoomMapper()
        return mapperRoom.toDomainList(serviceRoom.getCompany(company))
    }

    fun getOneID_Room(id: String): HeroeComun {
        val mapperRoom = HeroeRoomMapper()
        return mapperRoom.mapToDomainModel(serviceRoom.findById(id.toInt()))
    }

    fun insertALL_Room(heroes: List<HeroeComun>) {
        val mapperRoom = HeroeRoomMapper()
        serviceRoom.insertAll( mapperRoom.fromDomainList(heroes) )
    }

    fun deleteALL_Room() {
        serviceRoom.deleteAll()
    }

}