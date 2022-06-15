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
import com.example.pokedexcm.databinding.ActivitySelectionRegionBinding
import com.example.pokedexcm.model.PokeApi
import com.example.pokedexcm.model.PokeRegions
import com.example.pokedexcm.model.Pokemon
import com.example.pokedexcm.model.PokemonRegionApi
import com.example.pokedexcm.views.adapter.Adapter
import com.example.pokedexcm.views.adapter.AdapterRegion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SelectionRegion : AppCompatActivity(), AdapterRegion.OnItemListener {
    private val URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"
    private lateinit var binding: ActivitySelectionRegionBinding
    private lateinit var circle_bar: ProgressBar
    private lateinit var rv_list: RecyclerView
    private var regions_list = ArrayList<PokeRegions>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        circle_bar = binding.pbCharge
        rv_list = binding.rvPokemonRegion


        rv_list.visibility = View.INVISIBLE
        circle_bar.visibility = View.VISIBLE

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)

        val call = pokeApi.getRegion("api/v2/region/")

        call.enqueue(object : Callback<PokemonRegionApi> {
            override fun onResponse(
                call: Call<PokemonRegionApi>,
                response: Response<PokemonRegionApi>
            ) {
                //Conexion Exitosa
                //Log.d(LOGTAG, "Respuesta: ${response.toString()}")
                rv_list.visibility = View.VISIBLE
                circle_bar.visibility = View.INVISIBLE

                regions_list = response.body()?.lista!!

                binding.rvPokemonRegion.layoutManager = LinearLayoutManager(this@SelectionRegion)
                binding.rvPokemonRegion.adapter =
                    AdapterRegion(this@SelectionRegion, regions_list, this@SelectionRegion)
            }

            override fun onFailure(call: Call<PokemonRegionApi>, t: Throwable) {
                Log.d(LOGTAG, "Conexion Fallida")
                Toast.makeText(
                    this@SelectionRegion,
                    getString(R.string.fail_connect),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onItemClick(region:PokeRegions) {
        val params=Bundle()
        params.putString("url", region.nom_region)
        params.putString("url", region.url_region)
        val intent= Intent(this,SelectPokedex::class.java)
        intent.putExtras(params)
        startActivity(intent)
    }
}



