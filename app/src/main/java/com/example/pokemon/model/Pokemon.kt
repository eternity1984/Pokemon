package com.example.pokemon.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abhinav Singh on 17,June,2020
 */
@Entity(tableName = "favorite_table")
class Pokemon(var name: String, var url: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}