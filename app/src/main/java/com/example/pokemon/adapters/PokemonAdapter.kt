package com.example.pokemon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.adapters.PokemonAdapter.PokemonViewHolder
import com.example.pokemon.databinding.ListItemBinding
import com.example.pokemon.model.Pokemon
import java.util.*

/**
 * Created by Abhinav Singh on 17,June,2020
 */
class PokemonAdapter(private val context: Context, private var list: ArrayList<Pokemon>) : RecyclerView.Adapter<PokemonViewHolder>() {

    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = ListItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.itemBinding.pokemonName.text = list[position].name
        Glide.with(context)
                .load(list[position].url)
                .into(holder.itemBinding.pokemonImage)
    }

    override fun getItemCount()= list.size

    inner class PokemonViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    fun updateList(updatedList: ArrayList<Pokemon>) {
        list = updatedList
        notifyDataSetChanged()
    }

    fun getPokemonAt(position: Int) = list[position]
}