package com.aac.superheroe.data.apiheroes

import com.aac.superheroe.domain.model.HeroeResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface HeroesService {

    @GET("all.json")
    suspend fun getAll(): List<HeroeResponse>

}
