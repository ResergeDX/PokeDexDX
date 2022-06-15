package com.example.pokedexcm.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.R
import com.example.pokedexcm.databinding.ActivitySelectPokedexBinding
import com.example.pokedexcm.model.PokeApi
import com.example.pokedexcm.model.PokeDex
import com.example.pokedexcm.model.EntradasPK
import com.example.pokedexcm.views.adapter.AdapterPokeDex
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectPokedex: AppCompatActivity(),AdapterPokeDex.OnItemListener {
    private val URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"
    private lateinit var binding: ActivitySelectPokedexBinding

    private var pkdex_list= ArrayList<EntradasPK>()
    private lateinit var circle_bar: ProgressBar
    private lateinit var rv_list: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)
        circle_bar = binding.pbCharge
        rv_list = binding.rvPokemonPokedex

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val bundle = intent.extras
        val url=bundle?.getSerializable("url") as String
        Log.d(LOGTAG,"${url}")
        val id= url?.split("/")?.get(6)
        Log.d(LOGTAG,"${id}")

        val pokeApi:PokeApi= retrofit.create(PokeApi::class.java)
        val call = pokeApi.getPokeDex(id)

        call.enqueue(object : Callback<PokeDex> {
            override fun onResponse(
                call: Call<PokeDex>,
                response: Response<PokeDex>
            ) {
                //Conexion Exitosa
                //Log.d(LOGTAG, "Respuesta: ${response.toString()}")
                rv_list.visibility = View.VISIBLE
                circle_bar.visibility = View.INVISIBLE

                pkdex_list = response.body()?.entrada_poke!!

                binding.rvPokemonPokedex.layoutManager = LinearLayoutManager(this@SelectPokedex)
                binding.rvPokemonPokedex.adapter =
                    AdapterPokeDex(this@SelectPokedex, pkdex_list, this@SelectPokedex)
            }

            override fun onFailure(call: Call<PokeDex>, t: Throwable) {
                Log.d(LOGTAG, "Conexion Fallida")
                Toast.makeText(
                    this@SelectPokedex,
                    getString(R.string.fail_connect),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }
    override fun onItemClick(pokedex: EntradasPK) {
        val params=Bundle()
        params.putString("url",pokedex.url_pk)
        val intent= Intent(this,PokemonList::class.java)
        intent.putExtras(params)
        startActivity(intent)
    }

}