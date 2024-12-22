package com.example.projetkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OutdoorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outdoor)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val activities = listOf(
            Activity("Course", R.drawable.appli), // Remplace "image" par une image existante dans drawable
            Activity("Vélo", R.drawable.image),   // Remplace "image" par une image existante dans drawable
            Activity("Randonnée", R.drawable.ic_launcher_background) // Remplace "image" par une image existante dans drawable
        )

        val adapter = ActivityAdapter(activities) { activity ->
            Toast.makeText(this, "Commencer ${activity.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
    }
}

data class Activity(val name: String, val imageResId: Int)

class ActivityAdapter(
    private val activities: List<Activity>,
    private val onStartClick: (Activity) -> Unit
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.bind(activity)
    }

    override fun getItemCount() = activities.size

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val startButton: Button = itemView.findViewById(R.id.btn_start)

        fun bind(activity: Activity) {
            imageView.setImageResource(activity.imageResId)
            startButton.setOnClickListener {
                onStartClick(activity)
            }
        }
    }
}
