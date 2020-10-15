package com.example.pokemon.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.pokemon.databinding.HomeBinding
import com.example.pokemon.model.Pokemon
import com.example.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * Created by Abhinav Singh on 17,June,2020
 */
@AndroidEntryPoint
class Home : Fragment() {

    companion object {
        private const val TAG = "Home"
    }

    private var binding: HomeBinding? = null
    private val viewModel: PokemonViewModel by lazy { ViewModelProvider(this).get(PokemonViewModel::class.java) }
    private val adapter: PokemonAdapter? by lazy { context?.let { PokemonAdapter(it, pokemonList) } }
    private val pokemonList = ArrayList<Pokemon>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        setUpItemTouchHelper()
        viewModel.getPokemons()
    }

    private fun setUpItemTouchHelper() {
        val simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedPokemonPosition = viewHolder.adapterPosition
                val pokemon = adapter?.getPokemonAt(swipedPokemonPosition)
                viewModel.insertPokemon(pokemon)
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, "Pokemon added to favorites.", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding?.pokemonRecyclerView)
    }

    private fun observeData() {
        viewModel.pokemonList.observe(viewLifecycleOwner, { pokemons ->
            Log.e(TAG, "onChanged: " + pokemons.size)
            adapter?.updateList(pokemons)
        })
    }

    private fun initRecyclerView() {
        binding?.pokemonRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.pokemonRecyclerView?.adapter = adapter
    }
}