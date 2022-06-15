package com.example.pokedexcm.views.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.R
import com.example.pokedexcm.databinding.ActivityPokemonListBinding
import com.example.pokedexcm.model.PokeApi
import com.example.pokedexcm.model.Pokemon
import com.example.pokedexcm.model.PokemonEntries
import com.example.pokedexcm.model.PokemonInfoApi
import com.example.pokedexcm.views.adapter.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonList : AppCompatActivity(), Adapter.OnItemListener{
    private val URL="https://pokeapi.co/"
    private val LOGTAG="LOGS"
    private lateinit var binding: ActivityPokemonListBinding
    private var pokemon_list= ArrayList<PokemonEntries>()
    private lateinit var circle_bar:ProgressBar
    private lateinit var rv_list:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        circle_bar=binding.pbCharge
        rv_list=binding.rvPokemonList

        rv_list.visibility=View.INVISIBLE
        circle_bar.visibility= View.VISIBLE
        val retrofit: Retrofit =Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bundle = intent.extras
        val url=bundle?.getSerializable("url") as String
        Log.d(LOGTAG,"${url}")
        val id= url?.split("/")?.get(6)
        Log.d(LOGTAG,"${id}")

        val PokeApi: PokeApi = retrofit.create(PokeApi::class.java)

        val call = PokeApi.getPokemon(id)

        call.enqueue(object: Callback<PokemonInfoApi> {
            override fun onResponse(
                call: Call<PokemonInfoApi>,
                response: Response<PokemonInfoApi>
            ) {
                //Conexion Exitosa
                //Log.d(LOGTAG, "Respuesta: ${response.toString()}")
                rv_list.visibility=View.VISIBLE
                circle_bar.visibility= View.INVISIBLE

                pokemon_list = response.body()?.lista!!
                for (poke in pokemon_list){

                    getSprite(poke.list_entries?.url,pokemon_list.indexOf(poke),pokemon_list)


                }
                binding.rvPokemonList.layoutManager = LinearLayoutManager(this@PokemonList)
                binding.rvPokemonList.adapter = Adapter(this@PokemonList, pokemon_list, this@PokemonList)

            }

            override fun onFailure(call: Call<PokemonInfoApi>, t: Throwable) {
                Log.d(LOGTAG, "Conexion Fallida")
                Toast.makeText(this@PokemonList, getString(R.string.fail_connect), Toast.LENGTH_LONG).show()
            }

        })


    }

    override fun onItemClick(pokemon: Pokemon) {
        val params=Bundle()
        params.putString("name",pokemon.name)
        params.putString("url",pokemon.url)
        val intent= Intent(this,PokemonDetail::class.java)
        intent.putExtras(params)
        startActivity(intent)
    }
    private fun getSprite(url: String?, spaceList:Int, lista:ArrayList<PokemonEntries>){
        val id= url?.split("/")?.get(6)
        Log.d(LOGTAG,"${id}")

        lista[spaceList].list_entries?.sprite="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        lista[spaceList].list_entries?.num_pokedex=Integer.parseInt(id)

    }
}
