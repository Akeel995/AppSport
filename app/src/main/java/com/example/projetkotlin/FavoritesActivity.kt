package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoritesLayout = findViewById<LinearLayout>(R.id.favoritesLayout)

        // Récupérer les sports favoris depuis SharedPreferences
        val favorites = sharedPreferences.getStringSet("favoritesList", mutableSetOf())

        // Ajouter chaque sport favori comme bouton
        favorites?.forEach { sport ->
            val sportButton = Button(this)
            sportButton.text = sport
            favoritesLayout.addView(sportButton)
        }
    }
}