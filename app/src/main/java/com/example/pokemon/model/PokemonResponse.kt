package com.example.pokemon.model;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 17,June,2020
 */
data class PokemonResponse(
        var count: Int,
        var next: String,
        var previous: String,
        var results: ArrayList<Pokemon>
)
