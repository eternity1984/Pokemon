package com.example.pokemon.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemon.db.PokeDao;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonResponse;
import com.example.pokemon.network.PokeApiService;


import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Abhinav Singh on 17,June,2020
 */

class Repository @Inject constructor(
        private val pokeDao: PokeDao,
        private val apiService: PokeApiService,
) {


    fun getPokemons(): Observable<PokemonResponse> {
        return apiService.pokemons;
    }

    fun insertPokemon(pokemon: Pokemon) {
        pokeDao.insertPokemon(pokemon);
    }

    fun deletePokemon(pokemonName: String) {
        pokeDao.deletePokemon(pokemonName);
    }

    fun deleteAll(){
        pokeDao.deleteAll();
    }

    fun getFavoritePokemon(): LiveData<List<Pokemon>> {
        return pokeDao.favoritePokemons;
    }
}
