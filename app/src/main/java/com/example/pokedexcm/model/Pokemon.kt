package com.example.pokedexcm.model

import com.google.gson.annotations.SerializedName

class PokemonInfoApi{
    @SerializedName("pokemon_entries")
    var lista: ArrayList<PokemonEntries>?=null
}

class PokemonEntries{
    @SerializedName("pokemon_species")
    var list_entries:Pokemon?=null
}
class Pokemon {
    @SerializedName("name")
    var name: String?=null

    @SerializedName("url")
    var url: String?=null

    var sprite: String?=null
    var num_pokedex:Int?=null

}
