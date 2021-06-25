package com.aac.superheroe.domain.model

import com.google.gson.annotations.SerializedName

data class HeroeResponse (
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String,
    @SerializedName("slug") var slug:String,
    val powerstats: powerstats,
    val appearance: appearance,
    val biography: biography,
    val work: work,
    val connections: connections,
    val images: images
)

data class powerstats(
    @SerializedName("intelligence") var intelligence:String,
    @SerializedName("strength") var strength:String,
    @SerializedName("speed") var speed:String,
    @SerializedName("durability") var durability:String,
    @SerializedName("power") var power:String,
    @SerializedName("combat") var combat:String
)

data class appearance(
    @SerializedName("gender") var gender:String,
    @SerializedName("race") var race:String,
    @SerializedName("height") var height: List<String>,
    @SerializedName("weight") var weight:List<String>,
    @SerializedName("eyeColor") var eyeColor:String,
    @SerializedName("hairColor") var hairColor:String
)

data class biography(
    @SerializedName("fullName") var fullName:String,
    @SerializedName("alterEgos") var alterEgos:String,
    @SerializedName("aliases") var aliases:List<String>,
    @SerializedName("placeOfBirth") var placeOfBirth:String,
    @SerializedName("firstAppearance") var firstAppearance:String,
    @SerializedName("publisher") var publisher:String,
    @SerializedName("alignment") var alignment:String
)

data class work(
    @SerializedName("occupation") var occupation:String,
    @SerializedName("base") var base:String
)

data class connections(
    @SerializedName("groupAffiliation") var groupAffiliation:String,
    @SerializedName("relatives") var relatives:String
)

data class images(
    @SerializedName("xs") var xs:String,
    @SerializedName("sm") var sm:String,
    @SerializedName("md") var md:String,
    @SerializedName("lg") var lg:String
)

data class HeroesResponseList(
    @SerializedName("")
    val heroesResponseList:List<HeroeResponse> = listOf()
)