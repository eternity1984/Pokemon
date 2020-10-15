package com.example.pokemon.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.ui.fragments.Favorites
import com.example.pokemon.ui.fragments.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var isFavoriteListVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, Home())
                .commit()

        binding?.changeFragment
                ?.setOnClickListener {
                    if (isFavoriteListVisible) {
                        isFavoriteListVisible = false
                        binding?.changeFragment?.text = "Favorites"
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.frameLayout, Home())
                                .commit()
                    } else {
                        isFavoriteListVisible = true
                        binding?.changeFragment?.text = "Home"
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.frameLayout, Favorites())
                                .commit()
                    }
                }
    }
}