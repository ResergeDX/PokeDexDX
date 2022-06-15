package com.example.pokedexcm.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexcm.databinding.PokemonCardBinding
import com.example.pokedexcm.model.Pokemon
import com.example.pokedexcm.model.PokemonEntries
import com.example.pokedexcm.views.ui.activities.PokemonList
//Adaptador para la aparición y selección de Pokemon
class Adapter(context:Context, pokemons:ArrayList<PokemonEntries>, onItemListener: PokemonList): RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val pokemon_list = pokemons
    private val M_OnItemListener=onItemListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent?.context)
        val binding=PokemonCardBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding,M_OnItemListener)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bindData(pokemon_list[position])
    }

    override fun getItemCount(): Int {
        return pokemon_list.size
    }

    interface OnItemListener{
        fun onItemClick(pokemon: Pokemon)
    }

    class ViewHolder(binding: PokemonCardBinding, onItemListener:OnItemListener):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private val ivSprite = binding.ivSprite
        private val tvName= binding.tvName
        private val tvNumPokedex=binding.tvNumPokedex
        private val context=binding.root.context
        private val onItemListener=onItemListener
        private lateinit var pokemon: Pokemon

        init{
            binding.root.setOnClickListener(this)

        }
        override fun onClick(p0: View?) {
            onItemListener.onItemClick(pokemon)
        }

        fun bindData(item: PokemonEntries){
            tvName.text=item.list_entries?.name
            Glide.with(context)
                .load(item.list_entries?.sprite)
                .into(ivSprite)
            tvNumPokedex.text="#"+item.list_entries?.num_pokedex
            pokemon= item.list_entries!!
        }


    }

}