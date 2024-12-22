package com.example.projetkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Récupérer les boutons de l'interface
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnCalendar = findViewById<Button>(R.id.btnCalendar)

        // Naviguer vers la page des sports
        btnStart.setOnClickListener {
            val intent = Intent(this, SportsActivity::class.java)
            startActivity(intent)
        }

        // Naviguer vers la page du calendrier
        btnCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}
