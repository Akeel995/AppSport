package com.example.projetkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.graphics.Color

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

                // Définir un fond bleu pour le bouton
                sportButton.setBackgroundColor(Color.BLUE) // Utilisation de la couleur bleue

                // Optionnel : Définir la couleur du texte à blanc pour le contraste
                sportButton.setTextColor(Color.WHITE)

                // Appliquer un margin pour l'espacement entre les boutons
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 10, 0, 10) // Marge de 10dp en haut et en bas
                sportButton.layoutParams = layoutParams

                // Ajouter le bouton au layout
                favoritesLayout.addView(sportButton)
            }
        }
    }
}
