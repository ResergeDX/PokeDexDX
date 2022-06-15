package com.example.pokedexcm.views.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.R
import com.example.pokedexcm.databinding.AbilityElementBinding
import com.example.pokedexcm.databinding.StatsElementBinding
import com.example.pokedexcm.model.Ability
import com.example.pokedexcm.model.Statistics

class AdapterAbility(context: Context, ability_list:List<Ability>): RecyclerView.Adapter<AdapterAbility.ViewHolder>()  {
    private val habilidades=ability_list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAbility.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent?.context)
        val binding = AbilityElementBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.bindData(habilidades[position])
    }

    override fun getItemCount(): Int {
        return habilidades.size
    }
    class ViewHolder(binding: AbilityElementBinding): RecyclerView.ViewHolder(binding.root){
        private val iv_Hidden=binding.ivIsHidden
        private val tvAbility=binding.tvAbility
        private var card=binding.cdAbility
        fun bindData(habilidad: Ability){
            if (habilidad.hab_oculta == true){
                iv_Hidden.setImageResource(R.drawable.no_visible)
                card.setBackgroundResource(R.drawable.card_ability_hidden)
                tvAbility.setTextColor(Color.BLACK)
            }else{
                iv_Hidden.setImageResource(R.drawable.visible)
                card.setBackgroundResource(R.drawable.card_ability)
                tvAbility.setTextColor(Color.WHITE)
            }
            tvAbility.text=habilidad.ability?.ab_nombre?.replace("-"," ")


        }
    }
}