package com.example.pokemon.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.model.Pokemon

/**
 * Created by Abhinav Singh on 17,June,2020
 */
@Database(entities = [Pokemon::class], version = 2, exportSchema = false)
abstract class PokemonDB: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}