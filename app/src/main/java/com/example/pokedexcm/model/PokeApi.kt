package com.example.pokedexcm.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApi {
/*
    @GET
    fun getPokemon(
        @Url url: String?,
        @Query("limit")limit: String?
    ): Call<PokemonInfoApi>
  */
    @GET("api/v2/pokedex/{id}")
    fun getPokemon(
        @Path("id")id:String?
    ): Call<PokemonInfoApi>

    @GET("api/v2/pokemon/{id}")
    fun getPokeInfo(
        @Path("id")id:String?
    ): Call<PokeDetail>

    @GET
    fun getRegion(
        @Url url:String?
    ): Call<PokemonRegionApi>

    @GET("api/v2/region/{id}")
    fun getPokeDex(
        @Path("id")id:String?
    ): Call<PokeDex>
}