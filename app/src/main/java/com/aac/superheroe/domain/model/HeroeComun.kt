package com.aac.superheroe.domain.model

data class HeroeComun(
    var id: String,
    var name: String,
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String,
    var fullName: String,
    var alterEgos: String,
    var aliases: String,
    var publisher: String,
    var alignment: String,
    var xs: String,
    var lg: String
)

/** Map HeroeComun to Heroe */
fun HeroeComun.asHeroeRoom(): HeroeRoom {
    return HeroeRoom(
        //id = id.toInt(),
        name = name,
        intelligence = intelligence,
        strength = strength,
        speed = speed,
        durability = durability,
        power = power,
        combat = combat,
        fullName = fullName,
        alterEgos = alterEgos,
        aliases = aliases,
        publisher = publisher,
        alignment = alignment,
        xs = xs,
        lg = lg
    )
}

/** Map HeroeComun to Heroe */
fun List<HeroeComun>.asHeroesRoom(): List<HeroeRoom> {
    return map {
        HeroeRoom(
            //id = it.id.toInt(),
            name = it.name,
            intelligence = it.intelligence,
            strength = it.strength,
            speed = it.speed,
            durability = it.durability,
            power = it.power,
            combat = it.combat,
            fullName = it.fullName,
            alterEgos = it.alterEgos,
            aliases = it.aliases,
            publisher = it.publisher,
            alignment = it.alignment,
            xs = it.xs,
            lg = it.lg
        )
    }
}
