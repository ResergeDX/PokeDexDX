package com.example.pokedexcm.views.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedexcm.R
import com.example.pokedexcm.databinding.ActivityPokeDetailBinding
import com.example.pokedexcm.model.*
import com.example.pokedexcm.views.adapter.AdapterAbility
import com.example.pokedexcm.views.adapter.AdapterStats
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPokeDetailBinding

    private val URL="https://pokeapi.co/"
    private val LOGTAG="LOGS"
    private var abilities_list= ArrayList<Ability>()
    private var estadisticas=ArrayList<Statistics>()
    private var lista_tipos=ArrayList<Types>()
    private var boolShiny=true
    private var imageDefault=""
    private var imageShiny=""
    private lateinit var imagenPokemon:ImageView
    private lateinit var buttonShiny: Button
    private lateinit var pb_carga: ProgressBar
    private var cardList=ArrayList<CardView>()
    private var peso_str=""
    private var altura_str=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityPokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            cardList.add(cvVistaPokemon)
            cardList.add(cvInfoBase)
            cardList.add(cvStats)
            cardList.add(cvAbilities)
            pb_carga=pbCharge
        }
        for (cd in cardList){
            cd.visibility=View.INVISIBLE

        }
        pb_carga.visibility=View.VISIBLE
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bundle = intent.extras
        val url=bundle?.getSerializable("url") as String
        Log.d(LOGTAG,"${url}")
        val id= url?.split("/")?.get(6)
        Log.d(LOGTAG,"${id}")
        val PokeAPI:PokeApi= retrofit.create(PokeApi::class.java)

        val call: Call<PokeDetail> = PokeAPI.getPokeInfo(id)

        call.enqueue(object: Callback<PokeDetail> {
            override fun onResponse(call: Call<PokeDetail>, response: Response<PokeDetail>) {
                Log.d(LOGTAG, "Conexion exitosa ${response.body()?.Nombre}")
                with(binding) {

                    for (cd in cardList){
                        cd.visibility=View.VISIBLE

                    }
                    pb_carga.visibility=View.INVISIBLE

                    tvPokemonName.text=response.body()?.Nombre
                    tvBaseExp.text=response.body()?.exp_base

                    peso_str=(response.body()?.peso?.toFloat()?.div(10)).toString()
                    altura_str=(response.body()?.altura?.toFloat()?.div(10)).toString()


                    tvHeight.text=getString(R.string.altura,altura_str)
                    tvWeight.text=getString(R.string.peso,peso_str)

                    buttonShiny=btShiny
                    btShiny.setOnClickListener(this@PokemonDetail)

                    abilities_list=response.body()?.abilities!!
                    estadisticas= response.body()?.estadisticas!!
                    lista_tipos = response.body()?.types!!

                    imagenPokemon=ivPokemon
                    imageDefault= response.body()?.sprites?.front_def.toString()
                    imageShiny= response.body()?.sprites?.front_shiny.toString()

                    Glide.with(this@PokemonDetail)
                        .load(response.body()?.sprites?.front_def)
                        .into(ivPokemon)
                    val adapter_Estadisticas=AdapterStats(this@PokemonDetail,estadisticas)
                    rvStats.layoutManager= LinearLayoutManager(this@PokemonDetail)
                    rvStats.adapter=adapter_Estadisticas

                    val adapter_Habilidad= AdapterAbility(this@PokemonDetail,abilities_list)
                    rvAbilities.layoutManager=LinearLayoutManager(this@PokemonDetail)
                    rvAbilities.adapter=adapter_Habilidad
                    TypeImage(lista_tipos[0],ivType1)
                    if(lista_tipos.size==2) {
                        TypeImage(lista_tipos[1], ivType2)
                        ivType2.visibility=View.VISIBLE
                    }else{
                        ivType2.visibility=View.INVISIBLE
                    }

                    /*
                    abilities_list=response.body()?.abilities!!
                    estadisticas= response.body()?.estadisticas!!
                    lista_tipos = response.body()?.types!!
                    Log.d(LOGTAG,"${abilities_list.get(0).ability?.ab_nombre}")
                    Log.d(LOGTAG,"${estadisticas.get(0).stat?.name_stat}")
                    Log.d(LOGTAG,"${lista_tipos.get(0).tipo?.tipo_nombre}")
                    */
                }
            }
            override fun onFailure(call: Call<PokeDetail>, t: Throwable) {

                Log.d(LOGTAG, "Conexion Fallida")
                Toast.makeText(this@PokemonDetail, getString(R.string.fail_connect), Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun TypeImage(tipo: Types,image:ImageView){
        when(tipo.tipo?.tipo_nombre){

            "bug"->{return image.setImageResource(R.drawable.bug)}
            "dark"->{return image.setImageResource(R.drawable.dark)}
            "dragon"->{return image.setImageResource(R.drawable.dragon)}
            "electric"->{return image.setImageResource(R.drawable.electric)}
            "fairy"->{return image.setImageResource(R.drawable.fairy)}
            "fighting"->{return image.setImageResource(R.drawable.fighting)}
            "fire"->{return image.setImageResource(R.drawable.fire)}
            "flying"->{return image.setImageResource(R.drawable.flying)}
            "ghost"->{return image.setImageResource(R.drawable.ghost)}
            "grass"->{return image.setImageResource(R.drawable.plant)}
            "ground"->{return image.setImageResource(R.drawable.ground)}
            "ice"->{return image.setImageResource(R.drawable.ice)}
            "normal"->{return image.setImageResource(R.drawable.normal)}
            "poison"->{return image.setImageResource(R.drawable.poison)}
            "psychic"->{return image.setImageResource(R.drawable.psychic)}
            "rock"->{return image.setImageResource(R.drawable.rock)}
            "steel"->{return image.setImageResource(R.drawable.steel)}
            "water"->{return image.setImageResource(R.drawable.water)}
            else-> return image.setImageResource(android.R.color.transparent)

        }
    }

    override fun onClick(p0: View?) {
        //Caso Shiny
        if (boolShiny==false){
            Glide.with(this@PokemonDetail)
                .load(imageDefault)
                .into(imagenPokemon)
            boolShiny=true
            buttonShiny.setBackgroundResource(R.drawable.button_shiny_sel)
            buttonShiny.setText(R.string.Shiny_btn)

        }else{
            //Caso por defecto
            Glide.with(this@PokemonDetail)
                .load(imageShiny)
                .into(imagenPokemon)
            boolShiny=false
            buttonShiny.setBackgroundResource(R.drawable.button_def_sel)
            buttonShiny.setText(R.string.Normal_btn)
        }
    }

    fun SayType(view: android.view.View) {
        var tipe_num:Int=0
        var tipe_text=""
        var type_Res: String
        if (binding.ivType1.isPressed){
            tipe_num=1
            tipe_text= lista_tipos[0].tipo?.tipo_nombre.toString()

        }else if(binding.ivType2.isPressed){
            tipe_num=2
            tipe_text=lista_tipos[1].tipo?.tipo_nombre.toString()
        }
        Log.d(LOGTAG,"${tipe_text}")
        when(tipe_text){
            "bug"->{type_Res=getString(R.string.bug)}
            "dark"->{type_Res=getString(R.string.dark)}
            "dragon"->{type_Res=getString(R.string.dark)}
            "electric"->{type_Res=getString(R.string.electric)}
            "fairy"->{type_Res=getString(R.string.fairy)}
            "fighting"->{type_Res=getString(R.string.fighting)}
            "fire"->{type_Res=getString(R.string.fire)}
            "flying"->{type_Res=getString(R.string.flying)}
            "ghost"->{type_Res=getString(R.string.ghost)}
            "grass"->{type_Res=getString(R.string.grass)}
            "ground"->{type_Res=getString(R.string.ground)}
            "ice"->{type_Res=getString(R.string.ice)}
            "normal"->{type_Res=getString(R.string.normal)}
            "poison"->{type_Res=getString(R.string.poison)}
            "psychic"->{type_Res=getString(R.string.psychic)}
            "rock"->{type_Res=getString(R.string.rock)}
            "steel"->{type_Res=getString(R.string.steel)}
            "water"->{type_Res=getString(R.string.water)}
            else->type_Res="NULL"
        }

        Toast.makeText(this, getString(R.string.Tipo,tipe_num,type_Res),Toast.LENGTH_SHORT).show()

    }


}