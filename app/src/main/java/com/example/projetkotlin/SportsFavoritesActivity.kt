package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class SportsFavoritesActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_favorites)

        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)

        val favoritesLayout = findViewById<LinearLayout>(R.id.favoritesLayout)

        // Récupérer la liste des sports favoris depuis SharedPreferences
        val favorites = sharedPreferences.getStringSet("favoritesList", mutableSetOf()) ?: mutableSetOf()

        // Vérifier si la liste de favoris est vide
        if (favorites.isEmpty()) {
            Toast.makeText(this, "Aucun sport dans les favoris", Toast.LENGTH_SHORT).show()
        } else {
            // Ajouter un bouton pour chaque sport favori
            for (sportName in favorites) {
                val sportButton = Button(this)
                sportButton.text = sportName
                favoritesLayout.addView(sportButton)
            }
        }
    }
}
