package com.example.pokedexcm.model

import com.google.gson.annotations.SerializedName

class PokeDex{
    @SerializedName("name")
    var nombre_region_pk:String?=null
    @SerializedName("pokedexes")
    var entrada_poke:ArrayList<EntradasPK>?=null
}

class EntradasPK{
    @SerializedName("name")
    var nombre_pk:String?=null
    @SerializedName("url")
    var url_pk:String?=null
}
