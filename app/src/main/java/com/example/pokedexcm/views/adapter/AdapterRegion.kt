package com.example.pokedexcm.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.databinding.RegionCardBinding
import com.example.pokedexcm.model.PokeRegions
import com.example.pokedexcm.views.ui.activities.SelectionRegion

//Adaptador para la aparición y selección de Regiones

class AdapterRegion (context: Context, regions: ArrayList<PokeRegions>, onItemListener: SelectionRegion):RecyclerView.Adapter<AdapterRegion.ViewHolder>() {
    private val regionList =  regions
    private val M_OnItemListener=onItemListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRegion.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent?.context)
        val binding = RegionCardBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding,M_OnItemListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(regionList[position])
    }

    override fun getItemCount(): Int {
        return regionList.size
    }

    interface OnItemListener{
        fun onItemClick(region: PokeRegions)
    }
    class ViewHolder(binding: RegionCardBinding, onItemListener: SelectionRegion):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private val tvRegion = binding.tvNameRegion
        private val onItemListener = onItemListener
        private lateinit var region: PokeRegions

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(region)
        }

        fun bindData(item: PokeRegions) {
            tvRegion.text = item.nom_region
            region = item

        }
    }
}