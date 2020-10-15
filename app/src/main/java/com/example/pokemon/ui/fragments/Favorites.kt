package com.example.pokemon.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.adapters.PokemonAdapter
import com.example.pokemon.databinding.FavoritesBinding
import com.example.pokemon.model.Pokemon
import com.example.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * Created by Abhinav Singh on 17,June,2020
 */
@AndroidEntryPoint
class Favorites : Fragment() {
    private var binding: FavoritesBinding? = null
    private var viewModel: PokemonViewModel? = null
    private var adapter: PokemonAdapter? = null
    private val pokemonList = ArrayList<Pokemon>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavoritesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        initRecyclerView()
        setUpItemTouchHelper()
        observeData()
        //viewModel.getFavoritePokemon();
    }

    private fun observeData() {
        viewModel!!.favoritePokemonList.observe(viewLifecycleOwner, { pokemons ->
            if (pokemons == null || pokemons.size == 0) binding!!.noFavoritesText.visibility = View.VISIBLE else {
                val list = ArrayList<Pokemon>()
                list.addAll(pokemons)
                adapter!!.updateList(list)
            }
        })
    }

    private fun setUpItemTouchHelper() {
        val simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedPokemonPosition = viewHolder.adapterPosition
                val pokemon = adapter!!.getPokemonAt(swipedPokemonPosition)
                viewModel!!.deletePokemon(pokemon.name)
                Toast.makeText(context, "Pokemon removed from favorites.", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding!!.favoritesRecyclerView)
    }

    private fun initRecyclerView() {
        binding!!.favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PokemonAdapter(context!!, pokemonList)
        binding!!.favoritesRecyclerView.adapter = adapter
    }
}