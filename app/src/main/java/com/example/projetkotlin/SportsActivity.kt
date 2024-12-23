package com.example.projetkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SportsActivity : AppCompatActivity() {

    private val sports = listOf(
        Sport("Football", R.drawable.football),
        Sport("Basketball", R.drawable.image7),
        Sport("Tennis", R.drawable.image9),
        Sport("Volley", R.drawable.image8),
        Sport("Handball", R.drawable.image6),
        Sport("Rugby", R.drawable.image5),
        Sport("Baseball", R.drawable.image4),
        Sport("Hockey", R.drawable.image3),
        Sport("Golf", R.drawable.image2),
        Sport("Boxe", R.drawable.image1)
    )

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

        // Initialiser SharedPreferences
        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)

        // Récupérer le GridLayout dans le fichier XML
        val sportsLayout = findViewById<GridLayout>(R.id.sportsLayout)

        // Ajouter chaque sport avec son image (en haut) et son bouton (en bas)
        for (sport in sports) {
            // Conteneur vertical pour chaque sport
            val sportLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = LinearLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED)
                    setMargins(16, 16, 16, 16) // Espacement entre les sports
                }
            }

            // Ajouter l'image du sport
            val sportImage = ImageView(this).apply {
                setImageResource(sport.imageResId)
                layoutParams = LinearLayout.LayoutParams(140, 140).apply {
                    setMargins(8, 8, 8, 8) // Espacement entre les images
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                contentDescription = "Image du sport ${sport.name}" // Description pour l'accessibilité
            }
            sportLayout.addView(sportImage)

            // Ajouter le bouton avec le texte du sport
            val sportButton = Button(this).apply {
                text = sport.name
                setBackgroundColor(getColor(R.color.buttonBackground)) // Assurez-vous d'avoir une couleur définie dans `colors.xml`
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setOnClickListener {
                    addToFavorites(sport)
                    Toast.makeText(this@SportsActivity, "${sport.name} ajouté aux favoris", Toast.LENGTH_SHORT).show()
                }
            }
            sportLayout.addView(sportButton)

            // Ajouter le sportLayout dans le GridLayout
            sportsLayout.addView(sportLayout)
        }

        // Ajouter le bouton "Voir les favoris" dans le layout XML
        val viewFavoritesButton = findViewById<Button>(R.id.viewFavoritesButton)
        viewFavoritesButton.setOnClickListener {
            // Naviguer vers la page des favoris
            val intent = Intent(this@SportsActivity, SportsFavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addToFavorites(sport: Sport) {
        val favorites = sharedPreferences.getStringSet("favoritesList", mutableSetOf()) ?: mutableSetOf()
        favorites.add(sport.name)
        with(sharedPreferences.edit()) {
            putStringSet("favoritesList", favorites)
            apply()
        }
    }
}

// Modèle pour représenter un sport
data class Sport(val name: String, val imageResId: Int)
