package com.example.pokedexcm.model

import com.google.gson.annotations.SerializedName

class PokemonRegionApi{
    @SerializedName("results")
    var lista: ArrayList<PokeRegions>?=null
}
class PokeRegions{

    //Nombre región
    @SerializedName("name")
    var nom_region:String?=null

    @SerializedName("url")
    var url_region:String?=null
}
