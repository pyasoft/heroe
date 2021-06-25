package com.aac.superheroe.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aac.superheroe.utils.AppConstants.BD_ROOM
import org.jetbrains.annotations.NotNull

@Entity(tableName = BD_ROOM)
data class HeroeRoom(
    @ColumnInfo(name = "name") @NotNull var name: String,
    @ColumnInfo(name = "intelligence") @NotNull val intelligence: String,
    @ColumnInfo(name = "strength") @NotNull val strength: String,
    @ColumnInfo(name = "speed") @NotNull val speed: String,
    @ColumnInfo(name = "durability") @NotNull val durability: String,
    @ColumnInfo(name = "power") @NotNull val power: String,
    @ColumnInfo(name = "combat") @NotNull val combat: String,
    @ColumnInfo(name = "fullName") @NotNull val fullName: String,
    @ColumnInfo(name = "alterEgos") @NotNull val alterEgos: String,
    @ColumnInfo(name = "aliases") @NotNull val aliases: String,
    @ColumnInfo(name = "publisher") @NotNull val publisher: String,
    @ColumnInfo(name = "alignment") @NotNull val alignment: String,
    @ColumnInfo(name = "xs") @NotNull val xs: String,
    @ColumnInfo(name = "lg") @NotNull val lg: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}

/** Map Heroe to HeroeComun */
fun HeroeRoom.asHeroeComun(): HeroeComun {
    return HeroeComun(
        id = id.toString(),
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

/** Map Heroe to HeroeComun */
fun List<HeroeRoom>.asHeroesComun(): List<HeroeComun> {
    return map {
        HeroeComun(
            id = it.id.toString(),
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
