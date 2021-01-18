package com.example.pokemon.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemon.db.PokeDao;
import com.example.pokemon.db.PokemonDB;
import com.example.pokemon.network.PokeApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * Created by Abhinav Singh on 17,June,2020
 */

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providePokemonDB(application: Application): PokemonDB{
         return Room.databaseBuilder(application,PokemonDB::class.java,"Favorite Database")
                 .fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 .build();
    }

    @Provides
    @Singleton
    fun providePokeDao(pokemonDB: PokemonDB): PokeDao {
        return pokemonDB.pokeDao();
    }
}
