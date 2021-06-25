package com.aac.superheroe.domain.model

class HeroeResponseMapper : DomainMapper<HeroeResponse, HeroeComun> {

    override fun mapToDomainModel(model: HeroeResponse): HeroeComun {
        return HeroeComun(
            id = model.id,
            name = model.name,
            intelligence = model.powerstats.intelligence,
            strength = model.powerstats.strength,
            speed = model.powerstats.speed,
            durability = model.powerstats.durability,
            power = model.powerstats.power,
            combat = model.powerstats.combat,
            fullName = model.biography.fullName,
            alterEgos = model.biography.alterEgos,
            aliases = model.biography.aliases.joinToString(),
            publisher = model.biography.publisher ?: "-",
            alignment = model.biography.alignment,
            xs = model.images.xs,
            lg = model.images.lg
        )
    }

    override fun mapFromDomainModel(domainModel: HeroeComun): HeroeResponse {
        return HeroeResponse("",
            "",
            "",
            powerstats("","","","","",""),
            appearance("","",listOf(),listOf(),"",""),
            biography("","",listOf(),"","","",""),
            work("",""),
            connections("",""),
            images("","","","")
        )
    }

    fun toDomainList(initial: List<HeroeResponse>): List<HeroeComun>{
        return initial.map { mapToDomainModel(it) }
    }

}
