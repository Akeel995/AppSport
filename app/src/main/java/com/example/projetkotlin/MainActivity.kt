package com.example.projetkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projetkotlin.fragments.CalendarFragment
import com.example.projetkotlin.fragments.HomeFragment
import com.example.projetkotlin.fragments.OutdoorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Boutons pour la navigation
        val btnHome = findViewById<Button>(R.id.btn_home)
        val btnCalendar = findViewById<Button>(R.id.btn_calendar)
        val btnOutdoor = findViewById<Button>(R.id.btn_outdoor)
        val btnGoOutdoor: Button = findViewById(R.id.btn_goOutdoor)

        // Afficher le fragment par défaut
        loadFragment(HomeFragment())

        // Gestion des clics pour basculer entre les fragments
        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
        }

        btnCalendar.setOnClickListener {
            loadFragment(CalendarFragment())
        }

        btnOutdoor.setOnClickListener {
            loadFragment(OutdoorFragment())
        }
        btnGoOutdoor.setOnClickListener {
            // Naviguer vers la page OutdoorActivity
            val intent = Intent(this, OutdoorActivity::class.java)
            startActivity(intent)
        }
    }

    // Fonction pour charger un fragment
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
