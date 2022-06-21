package com.example.pokedexcm.model

import com.google.gson.annotations.SerializedName
//Clase para obtención de variantes pokemon
class PokeVariety{
    @SerializedName("id")
    var num_pk_specie:Int?=null

    @SerializedName("varieties")
    var list_variantes:ArrayList<Variante>?=null
}

class Variante{
    @SerializedName("is_default")
    var default_state:Boolean?=null

    @SerializedName("pokemon")
    var pokemon_info:Pokemon_INFO?=null
}

class Pokemon_INFO{
    @SerializedName("name")
    var name_pk_info:String?=null
    @SerializedName("url")
    var url_pk_info:String?=null
}

class PokeDetail {
    //Numero de la Pokedex
    @SerializedName("id")
    var num_pokedex:Int?=null

    //Nombre
    @SerializedName("name")
    var Nombre: String? = null
    //Altura
    @SerializedName("height")
    var altura:String?=null
    //Peso
    @SerializedName("weight")
    var peso:String?=null
    //Experiencia Base
    @SerializedName("base_experience")
    var exp_base:String?=null
    //Sprites
    @SerializedName("sprites")
    var sprites:Sprites?=null
    //Estadisiticas (Lista)
    @SerializedName("stats")
    var estadisticas:ArrayList<Statistics>?=null
    //Tipos (Lista)
    @SerializedName("types")
    var types:ArrayList<Types>?=null
    //Habilidades (Lista)
    @SerializedName("abilities")
    var abilities:ArrayList<Ability>?=null
}
class Sprites{
    //Sprite Base
    @SerializedName("front_default")
    var front_def:String?=null
    //Sprite Shiny
    @SerializedName("front_shiny")
    var front_shiny:String?=null

}

class Statistics{
    //Estadistica base
    @SerializedName("base_stat")
    var base_stat:String?=null
    //Nombre de Estadistica
    @SerializedName("stat")
    var stat:Stat?=null
}
class Stat{
    //Nombre de estadistica
    @SerializedName("name")
    var name_stat:String?=null
}
class Types{
    //Lote de tipos (Max 2)
    @SerializedName("slot")
    var lote:String?=null
    //Tipo
    @SerializedName("type")
    var tipo:Type?=null
}
class Type{
    //Nombre de Tipo
    @SerializedName("name")
    var tipo_nombre:String?=null
}
class Ability{
    //Informacion de las habilidades
    @SerializedName("ability")
    var ability:AbilityInfo?=null
    //Si es una habilidad Oculta
    @SerializedName("is_hidden")
    var hab_oculta:Boolean?=false
}
class AbilityInfo{

    @SerializedName("name")
    var ab_nombre:String?=null
}