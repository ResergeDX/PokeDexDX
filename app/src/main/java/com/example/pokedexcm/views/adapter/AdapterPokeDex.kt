package com.example.pokedexcm.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.databinding.PokedexCardBinding
import com.example.pokedexcm.model.EntradasPK
import com.example.pokedexcm.model.PokeDex
import com.example.pokedexcm.views.ui.activities.SelectPokedex

class AdapterPokeDex (context: Context, pkdex:ArrayList<EntradasPK>,onItemListener:SelectPokedex):RecyclerView.Adapter<AdapterPokeDex.ViewHolder>(){
    private val pokedexes=pkdex
    private val M_OnItemListener=onItemListener

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder{
        val layoutInflater=LayoutInflater.from(parent?.context)
        val binding= PokedexCardBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding,M_OnItemListener)
    }
    override fun onBindViewHolder(holder:ViewHolder,position:Int){
        holder.bindData(pokedexes[position])
    }
    override fun getItemCount(): Int {
        return pokedexes.size
    }
    interface OnItemListener{
        fun onItemClick(pokedex: EntradasPK)
    }
    class ViewHolder(binding:PokedexCardBinding, onItemListener: SelectPokedex):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        private val tvPokedex=binding.tvNamePokedex
        private val onItemListener=onItemListener
        private lateinit var pokedex: EntradasPK

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(pokedex)
        }

        fun bindData(item:EntradasPK){
            val name=item.nombre_pk?.replace("-","\n")
            tvPokedex.text=name
            pokedex=item
        }


    }
}