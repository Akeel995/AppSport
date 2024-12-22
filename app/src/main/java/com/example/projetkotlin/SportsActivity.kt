package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class SportsActivity : AppCompatActivity() {

    private val sports = listOf(
        Sport("Football"),
        Sport("Basketball"),
        Sport("Tennis")
    )

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)

        val sportsLayout = findViewById<LinearLayout>(R.id.sportsLayout)

        // Créer un bouton pour chaque sport
        for (sport in sports) {
            val sportButton = Button(this)
            sportButton.text = sport.name

            // Ajouter l'action au clic sur le bouton
            sportButton.setOnClickListener {
                addToFavorites(sport)
                Toast.makeText(this, "${sport.name} ajouté aux favoris", Toast.LENGTH_SHORT).show()
            }

            // Ajouter chaque bouton au layout
            sportsLayout.addView(sportButton)
        }

        // Créer un bouton pour afficher les favoris
        val viewFavoritesButton = Button(this)
        viewFavoritesButton.text = "Voir les favoris"
        viewFavoritesButton.setOnClickListener {
            // Naviguer vers la page des favoris
            val intent = Intent(this, SportsFavoritesActivity::class.java)
            startActivity(intent)
        }

        // Ajouter le bouton "Voir les favoris" au layout
        sportsLayout.addView(viewFavoritesButton)
    }

    private fun addToFavorites(sport: Sport) {
        // Récupérer les favoris existants
        val favorites = sharedPreferences.getStringSet("favoritesList", mutableSetOf()) ?: mutableSetOf()

        // Ajouter le sport à la liste des favoris
        favorites.add(sport.name)

        // Sauvegarder la nouvelle liste dans SharedPreferences
        with(sharedPreferences.edit()) {
            putStringSet("favoritesList", favorites)
            apply()
        }
    }
}

data class Sport(val name: String)
