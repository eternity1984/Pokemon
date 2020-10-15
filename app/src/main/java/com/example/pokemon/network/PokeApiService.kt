package com.example.pokemon.network

import com.example.pokemon.model.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * Created by Abhinav Singh on 17,June,2020
 */
interface PokeApiService {
    @get:GET("pokemon")
    val pokemons: Observable<PokemonResponse>
}