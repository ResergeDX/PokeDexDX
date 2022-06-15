package com.example.pokedexcm.views.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexcm.R
import com.example.pokedexcm.databinding.StatsElementBinding

import com.example.pokedexcm.model.Statistics


class AdapterStats(context: Context, stats_list:List<Statistics>): RecyclerView.Adapter<AdapterStats.ViewHolder>()  {
    private val estadisticas=stats_list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStats.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent?.context)
        val binding = StatsElementBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.bindData(estadisticas[position])
    }

    override fun getItemCount(): Int {
        return estadisticas.size
    }
    class ViewHolder(binding:StatsElementBinding):RecyclerView.ViewHolder(binding.root){
        private val tvNameStat=binding.tvNameStat
        private val tvInfoStat=binding.tvInfoStat
        private var pbStatistics=binding.pbInfoStat
        fun bindData(stat:Statistics){


            tvInfoStat.text=stat.base_stat
            pbStatistics.progress= Integer.parseInt(stat.base_stat)

            when(stat.stat?.name_stat){
                "hp"->{
                    tvNameStat.setText(R.string.hp)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,0,0,255))
                }
                "attack"->{
                    tvNameStat.setText(R.string.attack)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,255,0,0))
                }
                "defense"->{
                    tvNameStat.setText(R.string.defense)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,0,255,0))
                }
                "special-attack"->{
                    tvNameStat.setText(R.string.sp_attack)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,255,255,0))
                }
                "special-defense"->{
                    tvNameStat.setText(R.string.sp_defense)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,0,255,255))
                }
                "speed"->{
                    tvNameStat.setText(R.string.speed)
                    pbStatistics.progressTintList = ColorStateList.valueOf(Color.argb(255,255,0,255))
                }


            }

        }
    }
}