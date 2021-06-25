package com.aac.superheroe.domain.model

class HeroeRoomMapper : DomainMapper<HeroeRoom, HeroeComun> {

    override fun mapToDomainModel(model: HeroeRoom): HeroeComun {
        return HeroeComun(
            id = model.id.toString(),
            name = model.name,
            intelligence = model.intelligence,
            strength = model.strength,
            speed = model.speed,
            durability = model.durability,
            power = model.power,
            combat = model.combat,
            fullName = model.fullName,
            alterEgos = model.alterEgos,
            aliases = model.aliases,
            publisher = model.publisher,
            alignment = model.alignment,
            xs = model.xs,
            lg = model.lg
        )
    }

    override fun mapFromDomainModel(domainModel: HeroeComun): HeroeRoom {
        return HeroeRoom(
            name = domainModel.name,
            intelligence = domainModel.intelligence,
            strength = domainModel.strength,
            speed = domainModel.speed,
            durability = domainModel.durability,
            power = domainModel.power,
            combat = domainModel.combat,
            fullName = domainModel.fullName,
            alterEgos = domainModel.alterEgos,
            aliases = domainModel.aliases,
            publisher = domainModel.publisher,
            alignment = domainModel.alignment,
            xs = domainModel.xs,
            lg = domainModel.lg
        )
    }

    fun toDomainList(initial: List<HeroeRoom>): List<HeroeComun>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<HeroeComun>): List<HeroeRoom>{
        return initial.map { mapFromDomainModel(it) }
    }

}
