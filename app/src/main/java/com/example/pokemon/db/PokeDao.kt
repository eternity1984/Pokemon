package com.example.pokemon.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemon.model.Pokemon

/**
 * Created by Abhinav Singh on 17,June,2020
 */
@Dao
interface PokeDao {
    @Insert
    fun insertPokemon(pokemon: Pokemon?)

    @Query("DELETE FROM favorite_table WHERE name = :pokemonName")
    fun deletePokemon(pokemonName: String?)

    @Query("DELETE FROM favorite_table")
    fun deleteAll()

    @get:Query("SELECT * FROM favorite_table")
    val favoritePokemons: LiveData<List<Pokemon>>
}